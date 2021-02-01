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

package me.dreamhopping.kotify.api.scopes

/**
 * Scopes enable your application to access specific API endpoints on behalf of a user
 * The set of scopes you pass in your call determines the access permissions that the user is required to grant
 */
enum class SpotifyScope(val id: String) {
    ugcImageUpload("ugc-image-upload"),

    userReadRecentlyPlayed("user-read-recently-played"),
    userTopRead("user-top-read"),
    userReadPlaybackPosition("user-read-playback-position"),

    userReadPlaybackState("user-read-playback-state"),
    userModifyPlaybackState("user-modify-playback-state"),
    userReadCurrentlyPlaying("user-read-currently-playing"),

    appRemoteControl("app-remote-control"),
    streaming("streaming"),

    playlistModifyPublic("playlist-modify-public"),
    playlistModifyPrivate("playlist-modify-private"),
    playlistReadPrivate("playlist-read-private"),
    playlistReadCollaborative("playlist-read-collaborative"),

    userFollowModify("user-follow-modify"),
    userFollowRead("user-follow-read"),

    userLibraryModify("user-library-modify"),
    userLibraryRead("user-library-read"),

    userReadEmail("user-read-email"),
    userReadPrivate("user-read-private")
}
