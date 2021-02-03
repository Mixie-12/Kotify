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

package me.dreamhopping.kotify.api.section.user

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import me.dreamhopping.kotify.api.KotifyAPI
import me.dreamhopping.kotify.api.section.KotifyAPISection
import me.dreamhopping.kotify.api.section.user.types.KotifyUserCurrentTrack
import me.dreamhopping.kotify.api.section.user.types.KotifyUserPlaylists
import me.dreamhopping.kotify.api.section.user.types.KotifyUserProfile
import me.dreamhopping.kotify.builder.credentials.KotifyCredentials

class KotifyAPIUserSection(private val api: KotifyAPI) : KotifyAPISection() {
    private val apiPath: String = "me"

    suspend fun fetchProfile(): KotifyUserProfile = this.makeRequest("${api.url}/$apiPath", api.credentials)
    suspend fun fetchPlaylists(): KotifyUserPlaylists =
        this.makeRequest("${api.url}/$apiPath/playlists", api.credentials)

    suspend fun fetchCurrentTrack(): KotifyUserCurrentTrack {
        // TODO: Handle 200 OK with no data
        // TODO: Handle 204 NO CONTENT

        return this.makeRequest(
            "${api.url}/$apiPath/player",
            KotifyCredentials(
                "BQAh_DNXdesbA-AJbSkRMxV8mykgYx9TYtg0CNBuhEt6eAKrX-LsOVYj33ergiXHAfRp05J8Ob1lYGpaM09iFQaPVshMOOTxOApvOp3mrm5HlS4MIAiT0SHqfDt7I3mO8VUeshYiXFG8D8_4xJLMQL0ts3BNp5mH3TSsBg8v7i7W6SShq8F1yBWHugwTW8s",
                null
            )
        )
    }
}
