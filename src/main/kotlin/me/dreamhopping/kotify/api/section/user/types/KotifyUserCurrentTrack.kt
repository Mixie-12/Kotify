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

package me.dreamhopping.kotify.api.section.user.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KotifyUserCurrentTrack(
    val context: Context? = null,
    val timestamp: Long,

    @SerialName("progress_ms")
    val progressMs: Long,

    @SerialName("is_playing")
    val isPlaying: Boolean,

    @SerialName("currently_playing_type")
    val currentlyPlayingType: String,

    val item: Item
) {
    @Serializable
    data class Context(
        @SerialName("external_urls")
        val externalUrls: ExternalUrls,

        val href: String,
        val type: String,
        val uri: String
    )

    @Serializable
    data class ExternalUrls(
        val spotify: String
    )

    @Serializable
    data class Item(
        val album: Album,
        val artists: List<Album>,

        @SerialName("available_markets")
        val availableMarkets: List<String>,

        @SerialName("disc_number")
        val discNumber: Long,

        @SerialName("duration_ms")
        val durationMs: Long,

        val explicit: Boolean,

        @SerialName("external_ids")
        val ExternalIDs: ExternalIDs,

        @SerialName("external_urls")
        val externalUrls: ExternalUrls,

        val href: String,
        val id: String,
        val name: String,
        val popularity: Long,

        @SerialName("preview_url")
        val previewURL: String,

        @SerialName("track_number")
        val trackNumber: Long,

        val type: String,
        val uri: String
    )

    @Serializable
    data class Album(
        @SerialName("album_type")
        val albumType: String? = null,

        @SerialName("external_urls")
        val externalUrls: ExternalUrls,

        val href: String,
        val id: String,
        val images: List<Image>? = null,
        val name: String,
        val type: String,
        val uri: String
    )

    @Serializable
    data class Image(
        val height: Long,
        val url: String,
        val width: Long
    )

    @Serializable
    data class ExternalIDs(
        val isrc: String
    )
}
