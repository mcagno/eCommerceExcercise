package com.ecommerce.infrastructure

import com.ecommerce.model.Product

class InMemoryRepository : IProductRepository {

    private val products : MutableList<Product> = mutableListOf();

    override fun add(product: Product): Product {
        products.add(product)
        return product
    }

    override fun get(): Collection<Product> = products.toList()

}