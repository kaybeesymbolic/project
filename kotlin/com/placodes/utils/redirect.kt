package com.placodes.utils

import com.placodes.auth.getUserInfo
import com.placodes.models.*
import com.placodes.models.toDto
import io.ktor.server.application.*
import io.ktor.server.mustache.*
import io.ktor.server.response.*


suspend fun ApplicationCall.redirect(dir: String) {
    val user = getUserInfo(this)
    val userDto = user?.toDto()
    val mapper = next(userDto)
    return this.respond(MustacheContent(dir, mapper))
}

suspend fun ApplicationCall.redirectWithNex(dir: String) {
    val user = getUserInfo(this)
    val userDto = user?.toDto()
    val mapper = next(userDto)
    return this.respond(MustacheContent(dir, mapper))
}


suspend fun ApplicationCall.redirectWithPrevious(dir: String){
    val user = getUserInfo(this)
    val userDto = user?.toDto()
    val mapper = previous(userDto)
    return this.respond(MustacheContent(dir,mapper))
}