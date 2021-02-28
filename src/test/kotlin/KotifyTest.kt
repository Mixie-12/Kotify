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

import io.kotest.core.spec.style.ShouldSpec
import kotlinx.coroutines.runBlocking
import dev.dreamhopping.kotify.api.authorization.error.KotifyAuthenticationException
import dev.dreamhopping.kotify.api.authorization.flows.authorizationCodeFlow
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.net.URL
import kotlin.test.assertTrue

class AuthorizationCodeFlowTest : ShouldSpec() {
    init {
        context("Builder") {
            should("not throw when required details are specified") {
                assertDoesNotThrow {
                    authorizationCodeFlow {
                        clientID = System.getProperty("clientID")
                        clientSecret = System.getProperty("clientSecret")
                        redirectURI = "http://localhost:9103/callback/"

                        scopes {
                            all()
                        }
                    }
                }
            }

            should("throw when nothing is specified") {
                assertThrows<Throwable> {
                    authorizationCodeFlow {}
                }
            }
        }

        context("Authorization URL") {
            should("be a valid URL") {
                val authFlow = authorizationCodeFlow {
                    clientID = System.getProperty("clientID")
                    clientSecret = System.getProperty("clientSecret")
                    redirectURI = "http://localhost:9103/callback/"
                    showDialog = true

                    scopes {
                        all()
                    }
                }
                println("URL: ${authFlow.authorizationURL}")
                assertDoesNotThrow { URL(authFlow.authorizationURL) }
            }
        }

        context("Exchanging code for access token") {
            should("return a valid access token and not throw") {
                val authFlow = authorizationCodeFlow {
                    clientID = System.getProperty("clientID")
                    clientSecret = System.getProperty("clientSecret")
                    redirectURI = "http://localhost:9103/callback/"

                    scopes {
                        all()
                    }
                }

                assertDoesNotThrow {
                    runBlocking {
                        val response = authFlow.authorize(System.getProperty("code"))
                        println(response.accessToken)
                        assertTrue(response.accessToken.length > 1)
                    }
                }
            }

            should("throw KotifyAuthenticationException") {
                val authFlow = authorizationCodeFlow {
                    clientID = System.getProperty("clientID")
                    clientSecret = System.getProperty("clientSecret")
                    redirectURI = "http://localhost:9103/callback/"

                    scopes {
                        all()
                    }
                }

                assertThrows<KotifyAuthenticationException> {
                    runBlocking {
                        authFlow.authorize("invalid code")
                    }
                }
            }
        }
    }
}
