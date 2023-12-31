package com.ecommerce.test

import com.ecommerce.test.mock.MockedProductService
import com.ecommerce.model.Product
import com.ecommerce.services.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.contentnegotiation.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductRoutingTest : KoinTest {
    private val productRoute : String = "/product"
    private val queryRoute : String = "$productRoute/query"

    @Test
    fun `Add returns 200 and the product`() = testApplication {
        startKoin {
            modules(
                module {
                    single<IProductService> { MockedProductService() }
                }
            )
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val product = Product(1, "Trousers", 10.0, "M", "Red", false, true )
        val response = client.post(productRoute) {
            contentType(ContentType.Application.Json)
            setBody(product)
        }
        assertEquals(product, Json.decodeFromString(response.bodyAsText()))
        assertEquals(HttpStatusCode.OK, response.status)

    }


    @Test
    fun `initial get returns nothing`()= testApplication {

        val response = client.get(productRoute)

        assertEquals("[]", response.bodyAsText())
        assertEquals(HttpStatusCode.OK, response.status)

    }

    @Test
    fun `Query no result`() = testApplication {

        val productFilter = CompoundProductFilter(
            listOf(ProductFilter("price", ProductFilterOperator.LT, "12"))
        )
        val response = client.get(queryRoute) {
            parameter("q", Json.encodeToString(productFilter))
        }
        assertEquals("[]", response.bodyAsText())
        assertEquals(HttpStatusCode.OK, response.status)

    }


}
