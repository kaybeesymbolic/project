package com.placodes.plugins


import com.placodes.models.UserSession
import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.util.*

private val secretEncryptKey = hex("82340128734512778899babaccddeeff")
private val secretSignKey = hex("8234b57a326738c1968f45358219")
fun Application.configureSession() {
  install(Sessions) {
    cookie<UserSession>("user_session", SessionStorageMemory()) {
      cookie.path = "/"
      cookie.maxAgeInSeconds = 600
      transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretSignKey))
    }
  }
}