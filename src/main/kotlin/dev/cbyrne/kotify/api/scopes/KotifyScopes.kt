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
 * The class which holds the list of scopes the user has
 */
data class KotifyScopes(val allScopes: List<SpotifyScope> = listOf())

class KotifyScopesBuilder {
    private var scopes = mutableListOf<SpotifyScope>()

    /**
     * Adds a scope to the list
     */
    operator fun SpotifyScope.unaryPlus() {
        scopes.add(this)
    }

    /**
     * Sets the scopes list to all of the available scopes
     */
    fun all() {
        scopes = SpotifyScope.values().toMutableList()
    }

    /**
     * Creates a KotifyScopes instance from this builder
     */
    fun build(): KotifyScopes {
        return KotifyScopes(scopes)
    }
}
