package com.placodes.pages

import com.placodes.auth.getToken
import com.placodes.auth.getUserInfo
import com.placodes.models.UserSession
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*


fun Route.loginRoute()= route("/"){
    get ("login") {  }

    get("callback") {
        val token = getToken(call)
        if(token != null)
            call.sessions.set(UserSession(token)).apply {
                call.respondRedirect("/index")
            }
        else call.respondRedirect("/error")
    }
}