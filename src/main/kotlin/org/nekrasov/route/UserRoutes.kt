package org.nekrasov.route

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(){
    route("/user") {
        get {
            call.respondText("User")
        }
    }
}