package com.ecommerce.services

import com.ecommerce.infrastructure.IProductRepository
import com.ecommerce.model.Product
import org.koin.core.component.KoinComponent

interface IProductService {
    fun getAllProducts(): Collection<Product>
    fun add(product: Product): Product
    fun query(productFilter: IProductFilter): Collection<Product>
}

class ProductService(private var productRepository: IProductRepository) : KoinComponent, IProductService {

    override fun getAllProducts(): Collection<Product> = productRepository.get()

    override fun add(product: Product): Product = productRepository.add(product)
    override fun query(productFilter: IProductFilter): Collection<Product> {
        return productRepository.query(productFilter)
    }

}