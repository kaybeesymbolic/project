package com.placodes.pages



import com.placodes.auth.getUserInfo
import com.placodes.models.*
import com.placodes.models.toDto
import com.placodes.utils.redirect
import com.placodes.utils.redirectWithNex
import com.placodes.utils.redirectWithPrevious
import io.ktor.server.application.*
import io.ktor.server.mustache.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.indexRoute()= route("/"){
    get {
        call.respondRedirect("/index")
    }

    get("index"){
        val user = getUserInfo(call)
        val userDto = user?.toDto()
        val mapper = next(userDto)
        call.respond(MustacheContent("index/index.hbs", mapper))
    }

    route("index"){
        get("/next"){
            val user = getUserInfo(call)
            val userDto = user?.toDto()
            val mapper = next(userDto)

            call.respond(MustacheContent("index/index.hbs", mapper))
        }

        get("/previous"){
            val user = getUserInfo(call)
            val userDto = user?.toDto()
            val mapper = previous(userDto)
            call.respond(MustacheContent("index/index.hbs",mapper))
        }
    }

}