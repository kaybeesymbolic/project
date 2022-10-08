package com.placodes.auth


import io.ktor.server.auth.*
import io.ktor.server.sessions.*


fun AuthenticationConfig.form() = this.form("form-auth") {
    userParamName = "username"
    passwordParamName = "password"
//
//    validate {credential ->
//        val user: UserModel?
//        if(credential.name.isEmpty() || credential.password.isEmpty()) user = null
//
//        else if(credential.name == "Enter email address" || credential.password == "Enter password") user = null
//
//        else {
//            val username = credential.name
//            val password = credential.password
//
//            user= UserModel(name = username, email = username, password = password, logged = true)
//        }
//
//        user
//    }
//    skipWhen { call -> call.sessions.get<UserModel>() !=null }
}