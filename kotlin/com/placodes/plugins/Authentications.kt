package com.placodes.plugins

import com.placodes.auth.*
import io.ktor.client.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.config.*


fun Application.configureAuthentications(config: HoconApplicationConfig, httpClient: HttpClient){
    install(Authentication){
        google(config, httpClient)
    }
}