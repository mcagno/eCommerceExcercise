package com.ecommerce

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ProductTest {
    private val productRoute : String = "/product"

    @Test
    fun `add returns OK`() = testApplication {
        client.post(productRoute).apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    /*
    @Test
    fun `initial get returns nothing`()= testApplication {
        client.get(productRoute).apply {
            assertEquals("[]", bodyAsText())
            assertEquals(HttpStatusCode.OK, status);
        }
    }*/
}
