package com.ecommerce.mock

import com.ecommerce.infrastructure.IProductRepository
import com.ecommerce.model.Product

class MockedProductRepository : IProductRepository {
    private val _products : MutableList<Product> = mutableListOf()

    override fun add(product: Product): Product {
        _products.add(product)
        return product
    }

    override fun get(): Collection<Product> = _products.toList()

}
