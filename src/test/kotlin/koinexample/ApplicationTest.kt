package koinexample

import io.ktor.client.request.*
import io.ktor.client.statement.*
import koinexample.models.User
import koinexample.repositories.InMemoryUserRepository
import koinexample.repositories.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.inject

internal class ApplicationTest : BaseApplicationTest() {

    @Test
    fun testApplicationStartWithKoin() = withApp {
        loadKoinModules(module {
            // Register in-memory user repository that will override the already registered user repository.
            factory<UserRepository> { InMemoryUserRepository() }
        })

        // Insert a few users.
        val userRepository by inject<UserRepository>()
        userRepository.insert(User(username = "thomas"))
        userRepository.insert(User(username = "aleksei"))
        val insertedUsers = userRepository.getAll()

        // Make the request to fetch the users.
        val response = client.get("/api/v1/users")

        // Assert that the response contains the users that we've inserted.
        assertEquals("List of users: ${insertedUsers.joinToString(", ") { it.username }}", response.bodyAsText())
    }

}
