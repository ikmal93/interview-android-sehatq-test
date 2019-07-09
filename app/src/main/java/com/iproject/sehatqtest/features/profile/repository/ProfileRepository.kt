package com.iproject.sehatqtest.features.profile.repository

import com.iproject.sehatqtest.storage.history.History
import io.reactivex.Observable

interface ProfileRepository {
    fun getAll(): Observable<List<History>>
}