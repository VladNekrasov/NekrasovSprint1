package org.nekrasov.dao

import org.nekrasov.models.*
import io.ktor.server.config.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val driverClassName = "org.postgresql.Driver"
        //val jdbcURL = "jdbc:postgresql://chatpguser:1234@localhost:5432/chatdb"
        //val jdbcURL = "jdbc:postgresql://localhost:5432/chatdb"
        val jdbcURL = "jdbc:postgresql://postgres:5432/chatdb?user=chatpguser"
        val database = Database.connect(
            url = "jdbc:postgresql://localhost:5432/chatdb",
            driver = "org.postgresql.Driver",
            user = "chatpguser",
            password = "1234"
        )
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}