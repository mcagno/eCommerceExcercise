package com.ecommerce.test

import com.ecommerce.test.mock.MockedProductRepository
import com.ecommerce.services.ProductService
import com.ecommerce.model.Product
import com.ecommerce.services.CompoundProductFilter
import com.ecommerce.services.ProductFilter
import com.ecommerce.services.ProductFilterOperator
import kotlin.test.*

class ProductServiceTest {

    @Test
    fun `Get all products with empty repository returns no items`() {
        val productRepository = MockedProductRepository()
        val productService = ProductService(productRepository)

        val products = productService.getAllProducts()

        assertEquals(products.size, 0)
    }

    @Test
    fun `GetAllProducts returns single product`() {
        val productRepository = MockedProductRepository()
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        productRepository.add(trousers)
        val productService = ProductService(productRepository)

        val products = productService.getAllProducts()

        assertEquals(1, products.size)
        assertEquals(trousers, products.toList()[0])
    }

    @Test
    fun `GetAllProducts returns single products`() {
        val productRepository = MockedProductRepository()
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        val skirt = Product(2, "Skirt", 12.0, "S", "Red")
        productRepository.add(trousers)
        productRepository.add(skirt)
        val productService = ProductService(productRepository)

        val products = productService.getAllProducts()

        assertEquals(2, products.size)

        val productList = products.toList()
        assertEquals(trousers, productList[0])
        assertEquals(skirt, productList[1])
    }

    @Test
    fun `Add a single product`() {
        val productRepository = MockedProductRepository()
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        val skirt = Product(2, "Skirt", 12.0, "S", "Red")
        productRepository.add(trousers)
        productRepository.add(skirt)

        val productService = ProductService(productRepository)
        val jumper = Product(3, "Jumper", 15.0, "S", "Red")
        productService.add(jumper)

        val allProducts = productService.getAllProducts()
        assertEquals(3, allProducts.size)
        assertEquals(jumper, allProducts.toList()[2])
    }

    @Test
    fun `Query product single condition`() {
        val productRepository = MockedProductRepository()
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        val skirt = Product(2, "Skirt", 12.0, "S", "Red")
        productRepository.add(trousers)
        productRepository.add(skirt)

        val productService = ProductService(productRepository)

        val productFilter = ProductFilter("price", ProductFilterOperator.LT, "11")

        val products = productService.query(productFilter)

        assertEquals(1, products.size)
        assertEquals(trousers, products.toList()[0])


    }

    @Test
    fun `Query product compound condition`() {
        val productRepository = MockedProductRepository()
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        val skirt = Product(2, "Skirt", 12.0, "S", "Red")
        productRepository.add(trousers)
        productRepository.add(skirt)

        val productService = ProductService(productRepository)

        val productFilter = CompoundProductFilter(listOf(ProductFilter("price", ProductFilterOperator.LT, "11"),
        ProductFilter("name", ProductFilterOperator.IN, "er")))

        val products = productService.query(productFilter)

        assertEquals(1, products.size)
        assertEquals(trousers, products.toList()[0])

    }

    @Test
    fun `Query product compound condition - no result`() {
        val productRepository = MockedProductRepository()
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        val skirt = Product(2, "Skirt", 12.0, "S", "Red")
        productRepository.add(trousers)
        productRepository.add(skirt)

        val productService = ProductService(productRepository)

        val productFilter = CompoundProductFilter(listOf(ProductFilter("price", ProductFilterOperator.LT, "11"),
            ProductFilter("name", ProductFilterOperator.IN, "ki")))

        val products = productService.query(productFilter)

        assertEquals(0, products.size)


    }


}