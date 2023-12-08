package org.nekrasov

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.nekrasov.dao.DatabaseFactory
import org.nekrasov.plugins.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureSerialization()
    configureAuth()
    configureWebSockets()
    usersModule()
}