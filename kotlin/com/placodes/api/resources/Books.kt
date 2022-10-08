package com.placodes.api.resources

import io.ktor.server.routing.*
import io.ktor.server.sessions.*


fun Route.getBooksApi() = route("/books"){
    get {
        //val head = call.request.header("Authorization")?: "error"
        //val token = head.split("Bearer ").last()
//        val token = call.sessions.get<Token>()
//        //val tk = JWT.decode(token?.token)
//        val tk = TokenManager.verify(token?.token)
//        val x = tk.getClaim("username").asString()
//        call.respond(hashMapOf("username" to x))
    }
}