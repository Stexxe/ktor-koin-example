package koinexample

import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import koinexample.plugins.appModule
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.junit5.KoinTestExtension

// The latter enables us to ask JUnit to create only one instance of the test class and reuse it between tests.
// This is also useful when writing tests in Kotlin because it doesn't have static methods.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal abstract class BaseApplicationTest : KoinTest {

    @JvmField
    @RegisterExtension
    val koinTestExtension = KoinTestExtension.create {
        modules(
            appModule
        )
    }

    val testModule = module {
        // Register default class instances here.
    }

    fun <R> withApp(applicationTestBuilder: suspend ApplicationTestBuilder.() -> R) = testApplication {
        application {
            module(testing = true)
        }
        environment {
            config = MapApplicationConfig()
        }

        loadKoinModules(testModule)

        if (!isMigrated) {
            migrateDatabase()
        }

        try {
            applicationTestBuilder.invoke(this)
        } finally {
            // Truncate the database after a test.
            truncateDatabase()
        }
    }

    private fun migrateDatabase() {
        // ...
        isMigrated = true
    }

    private fun truncateDatabase() {
        // ...
    }

    companion object {
        private var isMigrated = false
    }

}
