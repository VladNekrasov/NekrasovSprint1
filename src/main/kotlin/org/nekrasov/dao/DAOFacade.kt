package org.nekrasov.dao
import org.nekrasov.models.*
interface DAOFacade {
    suspend fun allUsers(): List<User>
    suspend fun user(id: Int): User?
    suspend fun addNewUser(name: String): User?
    suspend fun editUser(id: Int, name: String): Boolean
    suspend fun deleteUser(id: Int): Boolean
}