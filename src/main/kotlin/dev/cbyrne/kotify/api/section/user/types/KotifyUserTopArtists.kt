package dev.cbyrne.kotify.api.section.user.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class KotifyUserTopArtists(
    val items: List<Artist>? = null,
    val total: Long? = null,
    val limit: Long? = null,
    val offset: Long? = null,
    val previous: JsonObject? = null,
    val href: String? = null,
    val next: String? = null
) {
    @Serializable
    data class Artist(
        @SerialName("external_urls")
        val externalUrls: KotifyUserCurrentTrack.ExternalUrls? = null,

        val followers: Followers? = null,
        val genres: List<String>? = null,
        val href: String? = null,
        val id: String? = null,
        val images: List<KotifyUserCurrentTrack.Image>? = null,
        val name: String? = null,
        val popularity: Long? = null,
        val type: String? = null,
        val uri: String? = null
    ) {
        @Serializable
        data class Followers(
            val href: JsonObject? = null,
            val total: Long? = null
        )
    }
}
