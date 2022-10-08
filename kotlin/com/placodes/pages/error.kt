package com.placodes.pages

import io.ktor.server.application.*
import io.ktor.server.mustache.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking


fun Route.errorRoute() = route("/"){
    get("error"){
        call.respond(MustacheContent("error.hbs", mapOf("error" to "Oops !!!. Something went wrong")))
    }
}

fun PipelineContext<Unit, ApplicationCall>.withD(): Flow<PipelineContext<Unit, ApplicationCall>> = flow {
    for(x in 1..4){
        delay(1000)
        emit(this@withD)
    }
}

fun Route.testError() = route("/"){
    get("testing"){
        runBlocking {
            this@get.withD().collect {
                call.respondText ("Hello world")
            }
        }
    }
}