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

package me.dreamhopping.kotify.api.section

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import me.dreamhopping.kotify.api.section.error.KotifyAPIRequestException
import me.dreamhopping.kotify.api.section.error.SpotifyAPIRequestError
import me.dreamhopping.kotify.builder.credentials.KotifyCredentials

/**
 * A section of the Kotify API
 */
abstract class KotifyAPISection {
    /**
     * The HttpClient to be used by all API sections
     */
    val client = HttpClient(OkHttp) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @Throws(KotifyAPIRequestException::class)
    suspend inline fun <reified T> makeRequest(url: String, credentials: KotifyCredentials): T {
        try {
            return client.get(url) {
                headers {
                    append("Authorization", "Bearer ${credentials.accessToken}")
                }
            }
        } catch (e: ClientRequestException) {
            val response: SpotifyAPIRequestError = e.response.receive()
            throw KotifyAPIRequestException(response.error.status, response.error.message)
        }
    }
}
