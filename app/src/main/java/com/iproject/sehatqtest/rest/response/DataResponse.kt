package com.iproject.sehatqtest.rest.response

import com.iproject.sehatqtest.storage.category.Category
import com.iproject.sehatqtest.storage.product.ProductPromo

data class DataResponse(
    val data: Data
)

data class Data(
    val category: List<Category>,
    val productPromo: List<ProductPromo>
)