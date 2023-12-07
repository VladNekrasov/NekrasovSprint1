package org.nekrasov.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.nekrasov.route.chatRoutes
import org.nekrasov.route.userRoutes

fun Application.usersModule() {
    routing {
        get("/") {
            call.respondText("Hello")
        }
        userRoutes()
        chatRoutes()
    }
}