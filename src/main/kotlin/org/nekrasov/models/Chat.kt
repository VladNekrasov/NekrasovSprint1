package org.nekrasov.models

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.datetime
import org.nekrasov.models.Users.autoIncrement
import java.io.Serializable
import java.time.LocalDateTime

data class Chat(
    val id: Int,
    val chatName:String,
    val creationDate: LocalDateTime
): Serializable


object Chats : Table() {
    val id = integer("id").autoIncrement()
    val chatName = varchar("chatName", 20)
    val creationDate = datetime("creationDate")
    override val primaryKey = PrimaryKey(id)
}