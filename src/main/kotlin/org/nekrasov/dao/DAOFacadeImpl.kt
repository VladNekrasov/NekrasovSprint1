package org.nekrasov.dao

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.nekrasov.models.User
import org.nekrasov.models.Users
import org.nekrasov.dao.DatabaseFactory.dbQuery
import java.time.LocalDateTime
import java.util.*

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        username = row[Users.username],
        firstName = row[Users.firstName],
        lastName = row[Users.lastName],
        email = row[Users.email],
        registrationDate = row[Users.registrationDate]
    )
    override suspend fun allUsers(): List<User> = dbQuery{
        Users.selectAll().map(::resultRowToUser)
    }
    override suspend fun user(id: Int): User? = dbQuery {
        Users
            .select{Users.id eq id}
            .map(::resultRowToUser)
            .singleOrNull()
    }
    override suspend fun addNewUser(username:String,
                                    firstName: String,
                                    lastName: String,
                                    email: String,
                                    registrationDate: LocalDateTime
    ): User? = dbQuery {
        val insertStatement = Users.insert {
            it[Users.username] = username
            it[Users.firstName] = firstName
            it[Users.lastName] = lastName
            it[Users.email] = email
            it[Users.registrationDate] = registrationDate
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }
    override suspend fun editUser(id: Int,
                                  username:String,
                                  firstName: String,
                                  lastName: String,
                                  email: String,
                                  registrationDate: LocalDateTime): Boolean = dbQuery {
        Users.update({Users.id eq id}){
            it[Users.username] = username
            it[Users.firstName] = firstName
            it[Users.lastName] = lastName
            it[Users.email] = email
            it[Users.registrationDate] = registrationDate
        } > 0
    }
    override suspend fun deleteUser(id: Int): Boolean = dbQuery {
        Users.deleteWhere { Users.id eq id } > 0
    }
}
val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
    }
}