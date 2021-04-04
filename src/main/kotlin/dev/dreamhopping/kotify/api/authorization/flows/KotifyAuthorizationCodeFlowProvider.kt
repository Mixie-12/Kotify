/*
 * Copyright (c) 2021 dreamhopping <https://github.com/dreamhopping>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.dreamhopping.kotify.api.authorization.flows

import khttp.post
import khttp.structures.authorization.BasicAuthorization
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import dev.dreamhopping.kotify.api.authorization.KotifyAuthorizationFlow
import dev.dreamhopping.kotify.api.authorization.error.KotifyAuthenticationException
import dev.dreamhopping.kotify.api.authorization.error.SpotifyAuthenticationError
import dev.dreamhopping.kotify.api.scopes.KotifyScopesBuilder
import java.net.URLEncoder

/**
 * The response received from Spotify when we exchange a code for an access token
 *
 * @see KotifyAuthorizationCodeFlowProvider.authorize
 */
@Serializable
data class KotifyTokenResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("expires_in")
    val expiresIn: Int,
    @SerialName("refresh_token")
    val refreshToken: String? = null,
    val scope: String
)

/**
 * The builder for the KotifyAuthorizationCodeFlowProvider
 * @see KotifyAuthorizationCodeFlowProvider
 */
class KotifyAuthorizationCodeFlowBuilder {
    /**
     * The client id provided to you by Spotify
     */
    var clientID: String? = null

    /**
     * The client secret provided to you by Spotify
     *
     * Can be null, authorize request will throw but getAuthorizeURL will not throw
     */
    var clientSecret: String? = null

    /**
     * The URI that you want the Spotify API to redirect to, this is where you'll handle the response
     */
    var redirectURI: String? = null

    /**
     * Whether or not to force the user to approve the app again if they’ve already done so
     */
    var showDialog = false

    /**
     * The scopes (permissions) that you want your application to have
     * @see KotifyScopesBuilder
     */
    var scopesBuilder: KotifyScopesBuilder = KotifyScopesBuilder()

    /**
     * Creates a KotifyAuthorizationFlowCredentials instance from this builder
     */
    fun build() = KotifyAuthorizationCodeFlowProvider(this)

    /**
     * A list of scopes (permissions) that your application needs
     */
    fun scopes(init: KotifyScopesBuilder.() -> Unit) = KotifyScopesBuilder().apply(init)
        .also { scopesBuilder = it }
}

/**
 * A function to build the authorization flow provider
 * @see KotifyAuthorizationCodeFlowBuilder
 */
fun authorizationCodeFlow(init: KotifyAuthorizationCodeFlowBuilder.() -> Unit) =
    KotifyAuthorizationCodeFlowBuilder().apply(init).build()

/**
 * This is the implementation class for the "Authorization Code Flow" method of authorization
 * Information needed:
 *   - clientID
 *   - redirectURI
 * Optional:
 *   - state (todo)
 *   - scopes
 *   - show_dialog
 *
 * @see KotifyAuthorizationFlow
 */
class KotifyAuthorizationCodeFlowProvider(builder: KotifyAuthorizationCodeFlowBuilder) : KotifyAuthorizationFlow {
    /**
     * The client id provided to you by Spotify
     */
    private val clientID = builder.clientID ?: error("clientID cannot be null!")

    /**
     * The client secret provided to you by Spotify
     * Can be null, authorize request will throw but getAuthorizeURL will function fine
     */
    private val clientSecret = builder.clientSecret

    /**
     * The URI that you want the Spotify API to redirect to, this is where you'll handle the response
     */
    private val redirectURI = builder.redirectURI ?: error("redirect_uri cannot be null!")

    /**
     * The scopes (permissions) that you want your application to have
     */
    private val scopes = builder.scopesBuilder.build().allScopes

    /**
     * Whether or not to force the user to approve the app again if they’ve already done so
     */
    private val showDialog = builder.showDialog

    /**
     * Returns an authorization URL
     */
    val authorizationURL: String
        get() {
            return "https://accounts.spotify.com/authorize" +
                    "?response_type=code" +
                    "&client_id=${clientID.urlEncoded}" +
                    "&scope=${scopes.joinToString(" ") { it.id }.urlEncoded}" +
                    "&redirect_uri=${redirectURI.urlEncoded}" +
                    "&show_dialog=${showDialog.toString().urlEncoded}"
        }

    /**
     * Get an access token, refresh token, token type and expiry date from a code
     *
     * @param code: This is sent from the Spotify API when the user is redirected from the authorize endpoint to your redirect URI
     * @throws KotifyAuthenticationException
     */
    @Throws(KotifyAuthenticationException::class)
    fun authorize(code: String): KotifyTokenResponse {
        if (clientSecret == null) throw KotifyAuthenticationException("Error", "clientSecret can not be null")

        val body = mapOf("grant_type" to "authorization_code", "code" to code, "redirect_uri" to redirectURI)
        val request = post(
            url = "https://accounts.spotify.com/api/token",
            auth = BasicAuthorization(clientID, clientSecret),
            data = body
        )

        if (request.statusCode != 200) {
            val error: SpotifyAuthenticationError = Json.decodeFromString(request.text)
            throw KotifyAuthenticationException(error.error, error.description)
        }

        return Json.decodeFromString(request.text)
    }

    /**
     * Get a new access token for an existing refresh token
     *
     * @param refreshToken: This is received from the Spotify API when [authorize] is called
     * @throws KotifyAuthenticationException
     */
    @Throws(KotifyAuthenticationException::class)
    fun refresh(refreshToken: String): KotifyTokenResponse {
        if (clientSecret.isNullOrEmpty()) throw KotifyAuthenticationException("refresh", "clientSecret can not be null or empty")
        if (refreshToken.isEmpty()) throw KotifyAuthenticationException("refresh", "refreshToken can not be empty")

        val body = mapOf("grant_type" to "refresh_token", "refresh_token" to refreshToken)
        val request = post(
            url = "https://accounts.spotify.com/api/token",
            auth = BasicAuthorization(clientID, clientSecret),
            data = body
        )

        if (request.statusCode != 200) {
            val error: SpotifyAuthenticationError = Json.decodeFromString(request.text)
            throw KotifyAuthenticationException(error.error, error.description)
        }

        return Json.decodeFromString(request.text)
    }
}

val String.urlEncoded: String
    get() = URLEncoder.encode(this, "utf-8")
