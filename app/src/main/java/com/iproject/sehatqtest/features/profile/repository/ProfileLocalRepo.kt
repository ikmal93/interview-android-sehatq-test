package com.iproject.sehatqtest.features.profile.repository

import com.iproject.sehatqtest.storage.history.History
import io.reactivex.Observable

interface ProfileLocalRepo {
    fun getAll(): Observable<List<History>>
}