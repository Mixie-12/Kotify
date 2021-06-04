package dev.cbyrne.kotify.api.section.user.types

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class KotifyUserTopTracks(
    val items: List<KotifyUserCurrentTrack.Item>? = null,
    val total: Long? = null,
    val limit: Long? = null,
    val offset: Long? = null,
    val previous: JsonObject? = null,
    val href: String? = null,
    val next: String? = null
)
