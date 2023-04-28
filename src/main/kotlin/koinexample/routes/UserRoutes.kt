package koinexample.routes

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import koinexample.repositories.UserRepository
import org.koin.ktor.ext.inject

@Resource("/users")
class Users() {

}

fun Route.userRoutes() {
    val userRepository by inject<UserRepository>()

    get<Users> {
        // Get all users...
        val users = userRepository.getAll()
        call.respondText("List of users: ${users.joinToString(", ") { it.username }}")
    }
}
