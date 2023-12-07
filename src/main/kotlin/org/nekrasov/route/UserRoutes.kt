package org.nekrasov.route

import org.nekrasov.dao.dao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
fun Route.userRoutes(){
    route("/users") {
        get(){
            val user =  dao.addNewUser("sdad")
            call.respondText("Один пользователь")
        }
   }
}