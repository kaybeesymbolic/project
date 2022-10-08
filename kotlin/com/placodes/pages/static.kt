package com.placodes.pages

import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File


fun Route.staticRoutes() = static("/static") {
    resources("files")
}