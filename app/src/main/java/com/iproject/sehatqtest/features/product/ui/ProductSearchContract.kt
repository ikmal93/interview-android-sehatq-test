package com.iproject.sehatqtest.features.product.ui

import com.iproject.sehatqtest.storage.product.ProductPromo

interface ProductSearchContract {
    interface View {
        fun showLoading()
        fun showData(productPromoList: List<ProductPromo>)
        fun showError()
    }

    interface Presenter {
        fun getAll()
        fun onViewDestroyed()

    }
}