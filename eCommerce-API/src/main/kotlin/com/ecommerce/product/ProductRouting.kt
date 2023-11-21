package com.ecommerce.product

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Route.productRouting() {
    route("/product") {

        post {

            call.respond(HttpStatusCode.OK)
        }

        get {
            call.respond(HttpStatusCode.OK)
        }

        get("query/{queryString}") {
            call.respond(HttpStatusCode.OK)
        }

    }
}

