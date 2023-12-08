package org.nekrasov.dao
import org.nekrasov.models.*
import java.time.LocalDateTime

interface DAOFacade {
    suspend fun allUsers(): List<User>
    suspend fun user(id: Int): User?
    suspend fun addNewUser(username:String,
                           firstName: String,
                           lastName: String,
                           email: String,
                           registrationDate: LocalDateTime
    ): User?
    suspend fun editUser(id: Int,
                         username:String,
                         firstName: String,
                         lastName: String,
                         email: String,
                         registrationDate: LocalDateTime): Boolean
    suspend fun deleteUser(id: Int): Boolean
}