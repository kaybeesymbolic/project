package com.placodes.models
//
//import com.auth0.jwt.JWT
//import com.auth0.jwt.algorithms.Algorithm
//import com.typesafe.config.ConfigFactory
//import io.ktor.server.config.*
//import io.ktor.server.engine.*
//import java.security.Principal
//import java.util.*
//
//object TokenManager {
//
//    val config by lazy { HoconApplicationConfig(ConfigFactory.load()) }
//    val secret = config.property("jwt.secret").getString()
//    val issuer = config.property("jwt.issuer").getString()
//    val audience = config.property("jwt.audience").getString()
//    val myRealm = config.property("jwt.realm").getString()
//    private val verifier by lazy { JWT.require(Algorithm.HMAC256(secret)).build() }
//
//    internal fun create(user: UserDto) = JWT.create()
//        .withAudience(audience)
//        .withIssuer(issuer)
//        .withClaim("username", user.email)
//        .withClaim("userId", user.id)
//        .withExpiresAt(Date(System.currentTimeMillis() + 60000))
//        .sign(Algorithm.HMAC256(TokenManager.secret))
//
//    internal fun verifier() = JWT.require(Algorithm.HMAC256(secret))
//        .withAudience(audience)
//        .withIssuer(issuer)
//        .build()
//
//    internal fun verify(token:String?=null) = verifier.verify(JWT.decode(token))
//}