package com.iproject.sehatqtest.features.home.ui

import com.iproject.sehatqtest.rest.response.DataResponse

interface HomeContract {
    interface View {
        fun showLoading()
        fun showData(dataResponse: List<DataResponse>)
        fun showError()
    }

    interface Presenter {
        fun getData()
        fun onViewDetached()
    }
}