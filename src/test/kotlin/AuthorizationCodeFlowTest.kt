import dev.cbyrne.kotify.api.authorization.error.KotifyAuthenticationException
import dev.cbyrne.kotify.api.authorization.flows.authorizationCodeFlow
import dev.cbyrne.kotify.api.scopes.SpotifyScope
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

internal class AuthorizationCodeFlowTest {
    @Test
    fun testAuthorizationUrl() {
        val codeFlow = authorizationCodeFlow {
            clientID = "test"
            redirectURI = "http://localhost:3000"
            scopes {
                SpotifyScope.USER_LIBRARY_READ
            }
        }

        val url = codeFlow.authorizationURL
        println("Authorization URL: $url")

        assertEquals(
            url,
            "https://accounts.spotify.com/authorize?response_type=code&client_id=test&scope=&redirect_uri=http%3A%2F%2Flocalhost%3A3000&show_dialog=false"
        )
    }

    @Test
    fun testInvalidAuthorizationCode() {
        val codeFlow = authorizationCodeFlow {
            clientID = "test"
            clientSecret = "test"
            redirectURI = "http://localhost:3000"
        }

        assertThrows<KotifyAuthenticationException> {
            codeFlow.authorize("this is an invalid code")
        }
    }
}