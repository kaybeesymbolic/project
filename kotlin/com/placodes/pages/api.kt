package com.placodes.pages

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.placodes.api.apiLoginRoute
import com.placodes.api.apiRegisterRoute
import com.placodes.api.resources.getBooksApi
import io.ktor.server.auth.*
import io.ktor.server.routing.*



fun Route.apiRoute() = route("/placodes/api"){
    v1()
}
fun Route.v1() = route("/v1"){
    apiLoginRoute()
    apiRegisterRoute()
    authenticate("token-auth"){getBooksApi() }
}