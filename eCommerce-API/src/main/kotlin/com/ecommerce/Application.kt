package com.ecommerce

import com.ecommerce.DI.DIModule
import com.ecommerce.plugins.configureRouting
import com.ecommerce.plugins.configureSerialization
import com.example.plugins.configureHTTP
import io.ktor.server.application.*

import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Koin) {
        modules(DIModule)
    }
    configureHTTP()
    configureSerialization()
    configureRouting()
}
