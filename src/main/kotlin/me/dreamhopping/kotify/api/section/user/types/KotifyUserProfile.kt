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
data class KotifyUserProfile(
    /**
     * The email of the user (requires the user-read-email scope)
     */
    val email: String? = null,

    /**
     * The country the user's account was created in
     */
    val country: String? = null,

    /**
     * The explicit content settings for this user
     */
    @SerialName("explicit_content")
    val explicitContentSettings: KotifyUserProfileExplicitContentSettings? = null,

    /**
     * The user's display name on Spotify
     */
    @SerialName("display_name")
    val displayName: String,

    /**
     * URLs related to this user's account
     */
    @SerialName("external_urls")
    val externalUrls: Map<String, String>,

    /**
     * The user's follower count information
     */
    val followers: KotifyUserProfileFollowers,

    /**
     * The api path to get this user's profile
     */
    val href: String,

    /**
     * This user's unique ID on Spotify
     */
    val id: String,

    /**
     * Images related to this user's account, for example: Profile Picture
     */
    val images: List<KotifyUserProfileImage>,

    /**
     * This is the user's version of Spotify (premium or free)
     */
    val product: String? = null,

    /**
     * The object type of this response (user)
     */
    val type: String,

    /**
     * The Spotify URI for this user
     */
    val uri: String
) {
    @Serializable
    data class KotifyUserProfileFollowers(val href: String?, val total: Int)

    @Serializable
    data class KotifyUserProfileImage(val height: Int?, val url: String, val width: Int?)

    @Serializable
    data class KotifyUserProfileExplicitContentSettings(
        @SerialName("filter_enabled")
        val filterEnabled: Boolean,
        @SerialName("filter_locked")
        val filterLocked: Boolean
    )
}
