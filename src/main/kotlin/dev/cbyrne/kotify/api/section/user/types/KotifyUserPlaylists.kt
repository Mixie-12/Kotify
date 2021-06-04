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

package dev.cbyrne.kotify.api.section.user.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class KotifyUserPlaylists(
    val href: String? = null,
    val items: List<Item>? = null,
    val limit: Long? = null,
    val next: Long? = null,
    val offset: Long? = null,
    val previous: Long? = null,
    val total: Long? = null
) {
    @Serializable
    data class Item(
        val collaborative: Boolean? = null,

        @SerialName("external_urls")
        val externalUrls: ExternalUrls? = null,

        val href: String? = null,
        val id: String? = null,
        val images: JsonArray? = null,
        val name: String? = null,
        val owner: Owner? = null,
        val public: Boolean? = null,

        @SerialName("snapshot_id")
        val snapshotID: String? = null,

        val tracks: Tracks? = null,
        val type: String? = null,
        val uri: String? = null
    )

    @Serializable
    data class ExternalUrls(
        val spotify: String? = null
    )

    @Serializable
    data class Owner(
        @SerialName("external_urls")
        val externalUrls: ExternalUrls? = null,

        val href: String? = null,
        val id: String? = null,
        val type: String? = null,
        val uri: String? = null
    )

    @Serializable
    data class Tracks(
        val href: String? = null,
        val total: Long? = null
    )
}