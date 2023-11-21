package com.ecommerce

import com.ecommerce.DI.DIModule
import com.ecommerce.plugins.*
import io.ktor.server.application.*

import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Koin) {
        modules(DIModule)
    }

    configureSerialization()
    configureRouting()
}
