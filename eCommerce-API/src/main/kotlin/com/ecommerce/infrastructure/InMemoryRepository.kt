package com.ecommerce.infrastructure

import com.ecommerce.model.Product
import com.ecommerce.services.IProductFilter
import org.koin.core.component.KoinComponent

class InMemoryRepository : IProductRepository, KoinComponent {

    private val products : MutableList<Product> = mutableListOf()

    override fun add(product: Product): Product {
        products.add(product)
        return product
    }

    override fun get(): List<Product> = products.toList()
    override fun query(productFilter: IProductFilter): Collection<Product> = productFilter.apply(products)

}