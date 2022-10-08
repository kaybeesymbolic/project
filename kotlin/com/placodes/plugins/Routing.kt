package com.placodes.plugins

import com.placodes.pages.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureRouting() {
    routing {
        apiRoute()
        staticRoutes()
        indexRoute()
        authenticate("google-auth"){ loginRoute() }
        logoutRoute()
        bookRoute()
        errorRoute()
        testError()
    }
}
