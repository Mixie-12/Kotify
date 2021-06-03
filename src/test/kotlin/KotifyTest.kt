import dev.cbyrne.kotify.kotify
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

internal class KotifyTest {
    @Test
    fun testBuilder() {
        assertThrows<IllegalStateException> {
            kotify {
                credentials {
                    accessToken = null
                    refreshToken = null
                }
            }
        }
    }
}