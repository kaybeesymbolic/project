package com.placodes


import com.placodes.db.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.placodes.plugins.*
import com.typesafe.config.ConfigFactory
import io.ktor.client.*
import io.ktor.server.application.*
import io.ktor.server.config.*



fun main() {
    val hocon = HoconApplicationConfig(ConfigFactory.load())
    val PORT = System.getenv("PORT").toInt()
    val HOST = System.getenv("HOST").toString()
    val server = embeddedServer(Netty, PORT, HOST) {
        DatabaseFactory.init()
        config(hocon)
    }
    server.start(true)
}


fun Application.config(config: HoconApplicationConfig){
    configureAuthentications(config, httpClient)
    configureMustache()
    //configureSerialization()
    configureSession()
    configureRouting()
}


