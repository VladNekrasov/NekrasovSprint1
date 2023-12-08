package org.nekrasov.models

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.datetime
import java.io.Serializable
import java.time.LocalDateTime

data class Message(
    val id: Int,
    val text:String,
    val fromId: Int,
    val chatId: Int,
    val email: String,
    val sendDate: LocalDateTime
):
    Serializable

object Messages : Table() {
    val id = integer("id").autoIncrement()
    val text = varchar("text", 400)
    val fromId = integer("fromId").references(Users.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val chatId = integer("chatId").references(Chats.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val sendDate = datetime("sendDate")
    override val primaryKey = PrimaryKey(id)
}