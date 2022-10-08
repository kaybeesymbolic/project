package com.placodes.auth
//
//import com.placodes.models.TokenManager
//import io.ktor.http.*
//import io.ktor.server.auth.*
//import io.ktor.server.auth.jwt.*
//import io.ktor.server.response.*
//
//
//fun AuthenticationConfig.jwt() = jwt("jwt-auth"){
//    verifier(TokenManager.verifier())
//
//    validate { credential ->
//        if(credential.payload.getClaim("username") != null){
//            val email = credential.payload.getClaim("username")
//            val id = credential.payload.getClaim("userId")
//            //UserDto(id = id.asString(), email = email.asString(), logged = true)
//            null
//
//        }else null
//    }
//
//    challenge { defaultScheme, realm ->
//        call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
//    }
//}