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

package dev.cbyrne.kotify.api.section.user

import dev.cbyrne.kotify.api.KotifyAPI
import dev.cbyrne.kotify.api.section.KotifyAPISection
import dev.cbyrne.kotify.api.section.user.types.*
import dev.cbyrne.kotify.api.time.SpotifyAPITimeRange

class KotifyAPIUserSection(private val api: KotifyAPI) : KotifyAPISection() {
    private val apiPath: String = "me"

    fun fetchProfile(): KotifyUserProfile? = this.makeRequest("${api.url}/$apiPath", api.credentials)
    fun fetchPlaylists(): KotifyUserPlaylists? = this.makeRequest("${api.url}/$apiPath/playlists", api.credentials)
    fun fetchCurrentTrack(): KotifyUserCurrentTrack? = this.makeRequest("${api.url}/$apiPath/player", api.credentials)

    /**
     * Requires the "user-library-read" scope
     */
    fun fetchSavedAlbums(limit: Int = 20, offset: Int = 0): KotifyUserSavedAlbums? =
        this.makeRequest("${api.url}/$apiPath/albums?limit=${limit}&offset=${offset}", api.credentials)

    fun fetchTopTracks(
        time_range: SpotifyAPITimeRange = SpotifyAPITimeRange.MEDIUM,
        limit: Int = 20,
        offset: Int = 0
    ): KotifyUserTopTracks? {
        return this.makeRequest(
            "${api.url}/$apiPath/top/tracks?limit=${limit}&offset=${offset}&time_range=${time_range.value}",
            api.credentials
        )
    }

    fun fetchTopArtists(
        time_range: SpotifyAPITimeRange = SpotifyAPITimeRange.MEDIUM,
        limit: Int = 20,
        offset: Int = 0
    ): KotifyUserTopArtists? {
        return this.makeRequest(
            "${api.url}/$apiPath/top/artists?limit=${limit}&offset=${offset}&time_range=${time_range.value}",
            api.credentials
        )
    }
}
