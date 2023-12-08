package org.nekrasov.models

import java.io.Serializable

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.nekrasov.models.Messages.references

data class ChatSession(
    val userId: Int,
    val chatId: Int,
):
    Serializable

object ChatSessions : Table() {
    val userId = integer("userId").references(Users.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val chatId = integer("chatId").references(Chats.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(userId, chatId)
}