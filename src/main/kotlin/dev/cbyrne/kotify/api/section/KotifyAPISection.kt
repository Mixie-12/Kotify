/*
 *     Kotify is a lightweight and modern Kotlin API Wrapper for Spotify.
 *     Copyright (C) 2021  Conor Byrne <https://github.com/cbyrneee>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.cbyrne.kotify.api.section

import dev.cbyrne.kotify.api.section.error.KotifyAPIRequestException
import dev.cbyrne.kotify.builder.credentials.KotifyCredentials
import khttp.get
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * A section of the Kotify API
 */
abstract class KotifyAPISection {
    internal val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Throws(KotifyAPIRequestException::class)
    internal inline fun <reified T> makeRequest(url: String, credentials: KotifyCredentials): T? {
        val response = get(url, mapOf("Authorization" to "Bearer ${credentials.accessToken}"))
        if (response.statusCode == 204) {
            return null
        } else if (response.statusCode != 200) {
            throw KotifyAPIRequestException(response.statusCode, response.text)
        }

        return json.decodeFromString(response.text)
    }
}
