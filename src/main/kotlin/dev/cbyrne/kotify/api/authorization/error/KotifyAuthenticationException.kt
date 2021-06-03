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

package dev.cbyrne.kotify.api.authorization.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The standard error object returned when Spotify has an error during the authorization flow
 *
 * @see <a href="https://developer.spotify.com/documentation/web-api/#authentication-error-object">Spotify's Documentation</a>
 */
@Serializable
data class SpotifyAuthenticationError(val error: String, @SerialName("error_description") val description: String) : Exception(description)

/**
 * Thrown when Spotify returns an error response
 *
 * @see SpotifyAuthenticationError
 */
data class KotifyAuthenticationException(val error: String, val description: String) : Exception(description)
