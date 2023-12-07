package org.nekrasov.dao

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.nekrasov.models.User
import org.nekrasov.models.Users
import org.nekrasov.dao.DatabaseFactory.dbQuery

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        name = row[Users.name],
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
    override suspend fun addNewUser(name: String): User? = dbQuery {
        val insertStatement = Users.insert {
            it[Users.name] = name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }
    override suspend fun editUser(id: Int, name: String): Boolean = dbQuery {
        Users.update({Users.id eq id}){
            it[Users.name] = name
        } > 0
    }
    override suspend fun deleteUser(id: Int): Boolean = dbQuery {
        Users.deleteWhere { Users.id eq id } > 0
    }
}
val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if(allUsers().isEmpty()) {
            addNewUser("Vlama")
        }
    }
}