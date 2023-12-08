package org.nekrasov.models

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.datetime
import java.io.Serializable
import java.time.LocalDateTime

data class User(
    val id: Int,
    val username:String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val registrationDate: LocalDateTime
    ):
    Serializable

object Users : Table() {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 20)
    val firstName = varchar("firstName", 20)
    val lastName = varchar("lastName",20)
    val email = varchar("email",255)
    val registrationDate = datetime("registrationDate")
    override val primaryKey = PrimaryKey(id)
}