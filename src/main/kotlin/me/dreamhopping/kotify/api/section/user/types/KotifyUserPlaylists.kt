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
import kotlinx.serialization.json.JsonArray

@Serializable
data class KotifyUserPlaylists(
    val href: String,
    val items: List<Item>,
    val limit: Long,
    val next: Long? = null,
    val offset: Long,
    val previous: Long? = null,
    val total: Long
) {
    @Serializable
    data class Item(
        val collaborative: Boolean,

        @SerialName("external_urls")
        val externalUrls: ExternalUrls,

        val href: String,
        val id: String,
        val images: JsonArray,
        val name: String,
        val owner: Owner,
        val public: Boolean,

        @SerialName("snapshot_id")
        val snapshotID: String,

        val tracks: Tracks,
        val type: String,
        val uri: String
    )

    @Serializable
    data class ExternalUrls(
        val spotify: String
    )

    @Serializable
    data class Owner(
        @SerialName("external_urls")
        val externalUrls: ExternalUrls,

        val href: String,
        val id: String,
        val type: String,
        val uri: String
    )

    @Serializable
    data class Tracks(
        val href: String,
        val total: Long
    )

}