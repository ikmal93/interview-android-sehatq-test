package com.iproject.sehatqtest.features.profile.ui

import com.iproject.sehatqtest.storage.history.History

interface ProfileContract {
    interface View {
        fun showData(historyList: List<History>)
    }

    interface Presenter {
        fun getHistory()
        fun onViewDetached()
    }
}