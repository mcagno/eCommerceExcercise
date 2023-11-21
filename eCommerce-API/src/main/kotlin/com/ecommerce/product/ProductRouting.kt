package com.ecommerce.product

import com.ecommerce.services.ProductService
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Route.productRouting() {

    val productService : ProductService by inject<ProductService>()

    route("/product") {

        post {
            call.respondText("Sono stato chiamato")
        }

        get {
            call.respond(productService.getAllProducts())
        }

        get("query/{queryString}") {
            call.respond(HttpStatusCode.OK)
        }

    }
}

