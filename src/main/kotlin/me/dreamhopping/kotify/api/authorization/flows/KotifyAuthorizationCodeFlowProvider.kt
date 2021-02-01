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

package me.dreamhopping.kotify.api.authorization.flows

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import me.dreamhopping.kotify.api.authorization.KotifyAuthorizationFlow
import me.dreamhopping.kotify.api.authorization.error.KotifyAuthenticationException
import me.dreamhopping.kotify.api.authorization.error.SpotifyAuthenticationError
import me.dreamhopping.kotify.api.scopes.KotifyScopesBuilder
import java.util.*
import kotlin.jvm.Throws

/**
 * The response received from Spotify when we exchange a code for an access token
 *
 * @see KotifyAuthorizationCodeFlowProvider.authorize
 */
@Serializable
data class KotifyTokenResponse(
    val access_token: String,
    val token_type: String,
    val scope: String,
    val expires_in: String,
    val refresh_token: String
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
    fun scopes(init: KotifyScopesBuilder.() -> Unit): KotifyScopesBuilder {
        scopesBuilder = KotifyScopesBuilder().apply(init)
        return scopesBuilder
    }
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
     *
     * Can be null, authorize request will throw but getAuthorizeURL will function fine
     */
    private val clientSecret = builder.clientSecret

    /**
     * The URI that you want the Spotify API to redirect to, this is where you'll handle the response
     */
    private val redirectURI = builder.redirectURI ?: error("clientID cannot be null!")

    /**
     * The scopes (permissions) that you want your application to have
     */
    private val scopes = builder.scopesBuilder.build().allScopes

    /**
     * Whether or not to force the user to approve the app again if they’ve already done so
     */
    private val showDialog = builder.showDialog

    /**
     * The http client used to make requests to the Spotify API
     */
    private val client = HttpClient(OkHttp) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    /**
     * Get an access token, refresh token, token type and expiry date from a code
     *
     * @param code: This is sent from the Spotify API when the user is redirected from the authorize endpoint to your redirect URI
     * @throws KotifyAuthenticationException
     */
    @Throws(KotifyAuthenticationException::class)
    suspend fun authorize(code: String): KotifyTokenResponse {
        try {
            return client.post {
                headers {
                    append(
                        "Authorization",
                        "Basic ${"${clientID}:${clientSecret ?: error("clientSecret can not be null for authorization!")}".toBase64()}"
                    )
                }
                url("https://accounts.spotify.com/api/token")
                body = FormDataContent(Parameters.build {
                    append("grant_type", "authorization_code")
                    append("code", code)
                    append("redirect_uri", redirectURI)
                })
            }
        } catch (e: ClientRequestException) {
            val error: SpotifyAuthenticationError = e.response.receive()
            throw KotifyAuthenticationException(error.error, error.error_description)
        }
    }

    /**
     * Builds a URL to use for Authorization
     */
    fun getAuthorizeURL(): String {
        val builder = URLBuilder("https://accounts.spotify.com/authorize")
        builder.parameters.append("client_id", clientID)
        builder.parameters.append("redirect_uri", redirectURI)
        builder.parameters.append("response_type", "code")
        if (scopes.isNotEmpty()) builder.parameters.append("scopes", scopes.joinToString(separator = " "))
        if (showDialog) builder.parameters.append("showDialog", showDialog.toString())

        return builder.buildString()
    }

    private fun String.toBase64(): String = Base64.getEncoder().encodeToString(this.toByteArray())
}
