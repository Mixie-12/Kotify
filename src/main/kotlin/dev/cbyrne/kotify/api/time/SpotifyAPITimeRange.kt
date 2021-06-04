package dev.cbyrne.kotify.api.time

enum class SpotifyAPITimeRange(val value: String) {
    LONG("long_term"),
    MEDIUM("medium_term"),
    SHORT("short_term")
}