package com.iproject.sehatqtest.features.profile.repository

import com.iproject.sehatqtest.storage.DbHelper
import com.iproject.sehatqtest.storage.history.History
import io.reactivex.Observable

class ProfileLocalRepoImpl(private val dbHelper: DbHelper): ProfileLocalRepo {
    override fun getAll(): Observable<List<History>> {
        return Observable.fromCallable {
            dbHelper.historyDAO().getAll()
        }
    }
}