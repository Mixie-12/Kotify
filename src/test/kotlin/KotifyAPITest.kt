import dev.cbyrne.kotify.api.time.SpotifyAPITimeRange
import dev.cbyrne.kotify.kotify
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

internal class KotifyAPITest {
    private val kotify = kotify {
        credentials {
            accessToken = System.getenv("KOTIFY_ACCESS_TOKEN")
        }
    }

    @Test
    fun testCurrentTrack() {
        assertDoesNotThrow {
            println("Current track: ${kotify.api.user.fetchCurrentTrack()}")
        }
    }

    @Test
    fun testTopArtists() {
        assertDoesNotThrow {
            println("Top artists: ${kotify.api.user.fetchTopArtists()}")
        }
    }

    @Test
    fun testTopTracks() {
        assertDoesNotThrow {
            println("Top tracks: ${kotify.api.user.fetchTopTracks(SpotifyAPITimeRange.LONG)}")
        }
    }

    @Test
    fun testSavedAlbums() {
        assertDoesNotThrow {
            println("Saved albums: ${kotify.api.user.fetchSavedAlbums(1)}")
        }
    }

    @Test
    fun testUserProfile() {
        assertDoesNotThrow {
            println("Profile: ${kotify.api.user.fetchProfile()}")
        }
    }

    @Test
    fun testUserPlaylists() {
        assertDoesNotThrow {
            println("Profile: ${kotify.api.user.fetchPlaylists()}")
        }
    }
}