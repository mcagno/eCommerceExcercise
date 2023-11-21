package com.ecommerce.mock

import com.ecommerce.model.Product
import com.ecommerce.services.IProductService

class MockedProductService : IProductService {
    override fun getAllProducts(): Collection<Product> {
        return emptyList()
    }

    override fun add(product: Product): Product {
        return product
    }

}
