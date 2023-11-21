package com.ecommerce.services



class ProductFilter(val filterString: String) {

    private val filterOperators = listOf{"="}

    fun validate(): Boolean {
        return true
    }


}