package com.ecommerce.test.mock

import com.ecommerce.model.Product
import com.ecommerce.services.IProductFilter
import com.ecommerce.services.IProductService

class MockedProductService : IProductService {
    override fun getAllProducts(): Collection<Product> {
        return emptyList()
    }

    override fun add(product: Product): Product {
        return product
    }

    override fun query(productFilter: IProductFilter): Collection<Product> {
        return emptyList()
    }

}
