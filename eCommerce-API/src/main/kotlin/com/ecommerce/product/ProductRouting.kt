package com.ecommerce.product

import com.ecommerce.model.Product
import com.ecommerce.services.IProductService
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import org.koin.ktor.ext.inject

fun Route.productRouting() {

    val productService : IProductService by inject<IProductService>()

    route("/product") {

        post {
            val product : Product = call.receive<Product>()
            call.respond(productService.add(product))
        }

        get {
            call.respond(productService.getAllProducts())
        }

        get("query/{queryString}") {
            call.respond(HttpStatusCode.OK)
        }

    }
}

