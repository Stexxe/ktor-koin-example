package koinexample.plugins

import io.ktor.server.application.*
import koinexample.repositories.DatabaseUserRepository
import koinexample.repositories.UserRepository
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureServiceContainer() {
    install(Koin) {
        slf4jLogger()
        modules(
            appModule
        )
    }
}

val appModule = module {
    factory<UserRepository> { DatabaseUserRepository() }
}