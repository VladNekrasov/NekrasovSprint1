package org.nekrasov.route

import org.nekrasov.dao.dao
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import java.time.LocalDateTime

fun Route.userRoutes(){
    route("/users") {
        get{
            val users = dao.allUsers();
        }
        post{
            val currentDateTime = LocalDateTime.now()
            val status = dao.addNewUser("vlama", "Владислав","Некрасов","vlad.nekrasov.555@inbox.ru", currentDateTime)
        }
        get("{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val user = dao.user(id)
        }
        delete("{id}"){
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val status = dao.deleteUser(id)
        }
        put("{id}"){
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val user = dao.user(id)
            val currentDateTime = LocalDateTime.now()
            val status = dao.editUser(id, user?.username+" обновлен",user?.firstName+" обновлен",user?.lastName+" обновлен",user?.email+" обновлен", currentDateTime)
        }
    }
}