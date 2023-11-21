package com.ecommerce.DI

import com.ecommerce.infrastructure.IProductRepository
import com.ecommerce.infrastructure.InMemoryRepository
import com.ecommerce.services.ProductService

import org.koin.dsl.module

val DIModule = module {
    single<IProductRepository> { InMemoryRepository() }
    single{ ProductService(get()) }
}