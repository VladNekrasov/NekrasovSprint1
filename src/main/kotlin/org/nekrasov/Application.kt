package org.nekrasov

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.nekrasov.plugins.*

fun main(){
    embeddedServer(Netty,port = 8080, host="0.0.0.0", module = Application::module)
        .start(wait = true)
}
fun Application.module() {
    configureSerialization()
    configureAuth()
    configureWebSockets()
    usersModule()
}