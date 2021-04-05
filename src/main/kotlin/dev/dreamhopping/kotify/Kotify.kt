/*
 * Copyright (c) 2021 dreamhopping <https://github.com/dreamhopping>.
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

package dev.dreamhopping.kotify

import dev.dreamhopping.kotify.api.KotifyAPI
import dev.dreamhopping.kotify.builder.KotifyBuilder
import dev.dreamhopping.kotify.builder.credentials.KotifyCredentials

/**
 * The main class for Kotify
 *
 * @see KotifyBuilder
 * @see kotify
 */
class Kotify(builder: KotifyBuilder) {
    var credentials: KotifyCredentials = builder.credentialsBuilder.build()
    val api: KotifyAPI = KotifyAPI(credentials)
}

/**
 * The builder function for the Kotify class
 *
 * @see KotifyBuilder
 * @see Kotify
 */
fun kotify(init: KotifyBuilder.() -> Unit) = KotifyBuilder().apply(init).build()
