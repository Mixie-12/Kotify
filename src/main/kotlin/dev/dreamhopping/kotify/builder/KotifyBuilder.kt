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

package dev.dreamhopping.kotify.builder

import dev.dreamhopping.kotify.Kotify
import dev.dreamhopping.kotify.builder.credentials.KotifyCredentialsBuilder

class KotifyBuilder {
    /**
     * The Spotify API credentials
     */
    var credentialsBuilder = KotifyCredentialsBuilder()

    /**
     * @return a KotifyCredentials instance
     */
    fun credentials(init: KotifyCredentialsBuilder.() -> Unit): KotifyCredentialsBuilder {
        credentialsBuilder = KotifyCredentialsBuilder().apply(init)
        return credentialsBuilder
    }

    /**
     * @return a Kotify instance from this builder
     */
    fun build(): Kotify {
        return Kotify(this)
    }
}

