package com.iproject.sehatqtest.features.product.ui

import com.iproject.sehatqtest.storage.history.History
import com.iproject.sehatqtest.storage.product.ProductPromo

interface ProductDetailContract {
    interface View{
        fun showLoading()
        fun showData(productPromo: ProductPromo)
        fun showError()
    }

    interface Presenter{
        fun getProduct(id: String?)
        fun onViewDetached()
        fun insert(history: History)
    }
}