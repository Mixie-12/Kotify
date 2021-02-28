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

package dev.dreamhopping.kotify.api.authorization

import dev.dreamhopping.kotify.api.authorization.flows.KotifyAuthorizationCodeFlowBuilder

/**
 * There are 4 types of authorization flow present in the Spotify API as of the time of making this
 *   - Authorization Code Flow (refreshable)
 *   - Authorization Code Flow With Proof Key for Code Exchange (refreshable)
 *   - Implicit Grant (not refreshable)
 *   - Client Credentials Flow (refreshable)
 *
 * This class allows you to access these providers via their builders
 */
object KotifyAuthorizationFlowProvider {
    /**
     * The authorization flow provider
     *
     * @see KotifyAuthorizationCodeFlowBuilder
     */
    fun authorizationCodeFlow() = KotifyAuthorizationCodeFlowBuilder()
}
