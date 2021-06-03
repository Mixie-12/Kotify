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

package dev.cbyrne.kotify.builder

import dev.cbyrne.kotify.Kotify
import dev.cbyrne.kotify.builder.credentials.KotifyCredentials
import dev.cbyrne.kotify.builder.credentials.KotifyCredentialsBuilder

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

    companion object {
        /**
         * A builder for [KotifyCredentials]
         * This allows the user to change the [Kotify.credentials] variable if required without allocating a new Kotify instance
         */
        fun credentials(init: KotifyCredentialsBuilder.() -> Unit): KotifyCredentials {
            return KotifyCredentialsBuilder().apply(init).build()
        }
    }
}
