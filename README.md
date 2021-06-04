# Kotify

Kotify is a lightweight and modern Kotlin API Wrapper for
the [Spotify Web API](https://developer.spotify.com/documentation/web-api). It is currently under development, and the
implementation is subject to change.

## Installation

You can add Kotify to your project via [Jitpack](https://jitpack.io)

```groovy
repositories {
    mavenCentral()
    
    // Required to retrieve Kotify from GitHub
    maven("https://jitpack.io/")
}

dependencies {
    implementation("com.github.cbyrneee:Kotify:latest-commit-hash")
}
```

## Usage

Using Kotify to get a user's profile name:

```kotlin
val kotify = kotify {
    credentials {
        accessToken = "accesstoken" // Required
        refreshToken = "refreshtoken" // Optional
    }
}

// To access all the profile data, you need the userReadEmail & userReadPrivate scopes
val profile = kotify.api.user.fetchProfile()
val displayName = profile?.displayName
```

Retrieving an authorization URL ([using the authorization code flow method](https://developer.spotify.com/documentation/general/guides/authorization-guide/#authorization-code-flow)):

```kotlin
val flow = authorizationCodeFlow {
    clientID = "clientid"

    // clientSecret is Not required for fetching URL.
    // It is required for exchanging code -> access token
    clientSecret = "clientsecret"
    redirectURI = "http://example.com"

    /**
     * The scopes / permissions that you want your application to have
     */
    scopes {
        +SpotifyScope.userReadPlaybackState
        +SpotifyScope.userReadCurrentlyPlaying
        +SpotifyScope.userReadPrivate
        +SpotifyScope.userReadEmail
        +SpotifyScope.userReadPlaybackPosition
    }
}

// The user can then open this URL and a request will be made to your redirectURI with a "code" parameter
val url = flow.authorizationURL
```

Exchanging an authorization code for an access & refresh token ([using the authorization code flow method](https://developer.spotify.com/documentation/general/guides/authorization-guide/#authorization-code-flow)):

```kotlin
val authFlow = authorizationCodeFlow {
    clientID = "clientid"

    // clientSecret is Not required for fetching URL.
    // It is required for exchanging code -> access token
    clientSecret = "clientsecret"
    redirectURI = "http://example.com"

    /**
     * The scopes / permissions that you want your application to have
     */
    scopes {
        +SpotifyScope.userReadPlaybackState
        +SpotifyScope.userReadCurrentlyPlaying
        +SpotifyScope.userReadPrivate
        +SpotifyScope.userReadEmail
        +SpotifyScope.userReadPlaybackPosition
    }
}

// The code parameter passed to authorize is the one you received from the redirectURI
val tokenDetails = authFlow.authorize("code")

// Use the access and refresh token
val accessToken = tokenDetails.accessToken
val refreshToken = tokenDetails.refreshToken
```

### Handling exceptions

API exceptions:

```kotlin
val kotify = kotify {
    credentials {
        accessToken = "accesstoken" // Required
        refreshToken = "refreshtoken" // Optional
    }
}

try {
    val currentTrack = kotify.api.user.fetchCurrentTrack()
} catch (e: KotifyAPIRequestException) {
    // This throws if something has gone wrong f.ex. you don't have the correct permissions
    e.printStackTrace()
}
```

Authorization exceptions:
```kotlin
val authFlow = authorizationCodeFlow {
    ...
}

try {
    val tokenDetails = authFlow.authorize("code")
} catch (e: KotifyAuthenticationException) {
    // This throws if something has gone wrong f.ex. the code has expired
    e.printStackTrace()
}
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests when adding new features.

## License

[GPL 3.0](https://choosealicense.com/licenses/gpl-3.0/)
