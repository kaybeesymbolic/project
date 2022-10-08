package com.placodes.pages

import com.placodes.models.UserSession
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*


fun Route.logoutRoute()= route("/"){
    get("logout"){
        call.sessions.clear<UserSession>().also{
            call.respondRedirect("/index")
        }
    }
}