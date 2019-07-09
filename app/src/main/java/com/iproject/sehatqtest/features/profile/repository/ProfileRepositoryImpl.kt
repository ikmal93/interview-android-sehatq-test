package com.iproject.sehatqtest.features.profile.repository

import com.iproject.sehatqtest.storage.history.History
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileRepositoryImpl(private val profileLocalRepo: ProfileLocalRepoImpl): ProfileRepository {
    override fun getAll(): Observable<List<History>> {
            return profileLocalRepo.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }
}