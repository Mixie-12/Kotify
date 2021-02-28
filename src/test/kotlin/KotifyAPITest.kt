import io.kotest.core.spec.style.ShouldSpec
import kotlinx.coroutines.runBlocking
import dev.dreamhopping.kotify.kotify
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertTrue

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

class KotifyAPITest : ShouldSpec() {
    init {
        context("User Profile") {
            should("return correct display name and not throw") {
                val kotify = kotify {
                    credentials {
                        accessToken = System.getProperty("accessToken")
                    }
                }

                assertDoesNotThrow {
                    runBlocking {
                        val userInfo = kotify.api.user.fetchProfile()
                        println(userInfo)

                        assertTrue {
                            userInfo?.displayName == "dreamhopping"
                        }
                    }
                }
            }
        }

        context("Playlists") {
            should("not throw") {
                val kotify = kotify {
                    credentials {
                        accessToken = System.getProperty("accessToken")
                    }
                }

                assertDoesNotThrow {
                    runBlocking {
                        println(kotify.api.user.fetchPlaylists())
                    }
                }
            }
        }

        context("Current Track") {
            should("not throw") {
                val kotify = kotify {
                    credentials {
                        accessToken = System.getProperty("accessToken")
                    }
                }

                assertDoesNotThrow {
                    println(kotify.api.user.fetchCurrentTrack())
                }
            }
        }

        context("Saved Albums") {
            should("not throw") {
                val kotify = kotify {
                    credentials {
                        accessToken = System.getProperty("accessToken")
                    }
                }

                assertDoesNotThrow {
                    println(kotify.api.user.fetchSavedAlbums())
                }
            }
        }
    }
}
