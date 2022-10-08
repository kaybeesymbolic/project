package com.placodes.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*


fun AuthenticationConfig.basic() = this.basic("basic-auth"){

    validate { credential ->

        if(credential.name.isNotEmpty() && credential.password.isNotEmpty()){
            val username = credential.name
            val password = credential.password
           // UserModel(name= username, email = username, logged = true)
            null
        }else null
    }
//    skipWhen { call ->
//        call.sessions.get<Any>() != null
//    }
}