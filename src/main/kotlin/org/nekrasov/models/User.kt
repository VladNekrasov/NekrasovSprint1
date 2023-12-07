package org.nekrasov.models

import org.jetbrains.exposed.sql.*
import java.io.Serializable

data class User(val id: Int, val name: String):
    Serializable

object Users : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name",30)
    override val primaryKey = PrimaryKey(id)
}