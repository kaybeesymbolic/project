package com.placodes.auth


import com.placodes.models.UserInfo
import com.placodes.models.UserSession
import com.placodes.plugins.httpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.config.*
import io.ktor.server.sessions.*


internal fun getToken(call: ApplicationCall):String?{
    val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()
    return principal?.accessToken
}

internal suspend fun getUserInfo(call: ApplicationCall): UserInfo?{
    var userInfo: UserInfo? = null
    val user_session = call.sessions.get<UserSession>()
    if(user_session != null) {
        userInfo = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
            headers {
                append(HttpHeaders.Authorization, "Bearer ${user_session.token}")
            }
        }.body()
    }
    return userInfo
}



fun AuthenticationConfig.google(config: HoconApplicationConfig, httpClient: HttpClient) = this.oauth("google-auth") {
        urlProvider = { config.property("google.provider").getString() }
        providerLookup = {
            OAuthServerSettings.OAuth2ServerSettings(
                name = config.property("google.name").getString(),
                authorizeUrl = config.property("google.authorizeUrl").getString(),
                accessTokenUrl = config.property("google.accessTokenUrl").getString(),
                requestMethod = HttpMethod.Post,
                clientId = System.getenv("GOOGLE_CLIENT_ID"),
                clientSecret = System.getenv("GOOGLE_CLIENT_SECRET"),
                defaultScopes = listOf(config.property("google.defaultScopes").getString()),
                extraTokenParameters = listOf("access_type" to "offline")
            )
        }
        client = httpClient

}