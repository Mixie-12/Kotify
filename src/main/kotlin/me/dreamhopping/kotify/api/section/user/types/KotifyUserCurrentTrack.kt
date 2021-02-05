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
    val timestamp: Long? = null,

    @SerialName("progress_ms")
    val progressMs: Long? = null,

    @SerialName("is_playing")
    val isPlaying: Boolean? = null,

    @SerialName("currently_playing_type")
    val currentlyPlayingType: String? = null,

    val item: Item? = null
) {
    @Serializable
    data class Context(
        val href: String? = null,
        val type: String? = null,
        val uri: String? = null,

        @SerialName("external_urls")
        val externalUrls: ExternalUrls? = null
    )

    @Serializable
    data class ExternalUrls(
        val spotify: String? = null
    )

    @Serializable
    data class Item(
        val album: Album? = null,
        val artists: List<Album>? = null,

        @SerialName("available_markets")
        val availableMarkets: List<String>? = null,

        @SerialName("disc_number")
        val discNumber: Long? = null,

        @SerialName("duration_ms")
        val durationMs: Long? = null,

        val explicit: Boolean? = null,

        val href: String? = null,
        val id: String? = null,
        val name: String? = null,

        @SerialName("preview_url")
        val previewURL: String? = null,

        @SerialName("track_number")
        val trackNumber: Long? = null,

        val type: String? = null,
        val uri: String? = null,

        val popularity: Long? = null,

        @SerialName("external_ids")
        val ExternalIDs: ExternalIDs? = null,

        @SerialName("external_urls")
        val externalUrls: ExternalUrls? = null,
    )

    @Serializable
    data class Album(
        @SerialName("album_type")
        val albumType: String? = null,

        val href: String? = null,
        val id: String? = null,
        val images: List<Image>? = null,
        val name: String? = null,
        val type: String? = null,
        val uri: String? = null,

        @SerialName("external_urls")
        val externalUrls: ExternalUrls? = null
    )

    @Serializable
    data class Image(
        val width: Long? = null,
        val url: String? = null,
        val height: Long? = null
    )

    @Serializable
    data class ExternalIDs(
        val isrc: String? = null
    )
}
