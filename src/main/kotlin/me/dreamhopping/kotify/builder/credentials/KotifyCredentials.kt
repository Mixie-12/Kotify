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

package me.dreamhopping.kotify.builder.credentials

data class KotifyCredentials(
    val accessToken: String,
    val refreshToken: String
)

class KotifyCredentialsBuilder {
    private var accessToken: String? = null
    private var refreshToken: String? = null

    /**
     * Creates a KotifyAuthorizationFlowCredentials instance from this builder
     */
    fun build() = KotifyCredentials(
        accessToken ?: error("accessToken can't be null!"),
        refreshToken ?: error("refreshToken can't be null!")
    )
}
