package koinexample

import io.ktor.server.application.*
import io.ktor.server.routing.*
import koinexample.plugins.configureResources
import koinexample.plugins.configureServiceContainer
import koinexample.routes.userRoutes

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    if (!testing) configureServiceContainer()
    configureResources()

    routing {
        route("/api/v1") {
            userRoutes()
        }
    }
}
