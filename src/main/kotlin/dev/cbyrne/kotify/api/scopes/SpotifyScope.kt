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

package dev.cbyrne.kotify.api.scopes

/**
 * Scopes enable your application to access specific API endpoints on behalf of a user
 * The set of scopes you pass in your call determines the access permissions that the user is required to grant
 */
enum class SpotifyScope(val id: String) {
    /**
     * Write access to user-provided images
     */
    UGC_IMAGE_UPLOAD("ugc-image-upload"),

    /**
     * Read access to a user’s recently played tracks
     */
    USER_READ_RECENTLY_PLAYED("user-read-recently-played"),

    /**
     * Read access to a user’s recently played tracks
     */
    USER_TOP_READ("user-top-read"),

    /**
     * Read access to a user’s playback position in a content
     */
    USER_READ_PLAYBACK_POSITION("user-read-playback-position"),

    /**
     * Read access to a user’s player state
     */
    USER_READ_PLAYBACK_STATE("user-read-playback-state"),

    /**
     * Write access to a user’s playback state
     */
    USER_MODIFY_PLAYBACK_STATE("user-modify-playback-state"),

    /**
     * Read access to a user’s currently playing content
     */
    USER_READ_CURRENTLY_PLAYING("user-read-currently-playing"),

    /**
     * Remote control playback of Spotify. This scope is currently available to Spotify iOS and Android SDKs
     */
    APP_REMOTE_CONTROL("app-remote-control"),

    /**
     * Control playback of a Spotify track. This scope is currently available to the Web Playback SDK. The user must have a Spotify Premium account
     */
    STREAMING("streaming"),

    /**
     * Write access to a user's public playlists
     */
    PLAYLIST_MODIFY_PUBLIC("playlist-modify-public"),

    /**
     * Write access to a user's private playlists
     */
    PLAYLIST_MODIFY_PRIVATE("playlist-modify-private"),

    /**
     * Read access to user's private playlists
     */
    PLAYLIST_READ_PRIVATE("playlist-read-private"),

    /**
     * Include collaborative playlists when requesting a user's playlists
     */
    PLAYLIST_READ_COLLABORATIVE("playlist-read-collaborative"),

    /**
     * Write/delete access to the list of artists and other users that the user follows
     */
    USER_FOLLOW_MODIFY("user-follow-modify"),

    /**
     * Read access to the list of artists and other users that the user follows
     */
    USER_FOLLOW_READ("user-follow-read"),

    /**
     * Write/delete access to a user's "Your Music" library
     */
    USER_LIBRARY_MODIFY("user-library-modify"),

    /**
     * Read access to a user's "Your Music" library
     */
    USER_LIBRARY_READ("user-library-read"),

    /**
     * Read access to user’s email address
     */
    USER_READ_EMAIL("user-read-email"),

    /**
     * Read access to user’s subscription details (type of user account)
     */
    USER_READ_PRIVATE("user-read-private")
}
