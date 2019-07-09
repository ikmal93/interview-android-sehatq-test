package com.iproject.sehatqtest.features.profile.ui

import com.iproject.sehatqtest.features.profile.repository.ProfileLocalRepoImpl
import com.iproject.sehatqtest.features.profile.repository.ProfileRepositoryImpl
import com.iproject.sehatqtest.storage.DbHelper
import com.iproject.sehatqtest.storage.history.History
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class ProfilePresenter(
    private val profileView: ProfileContract.View,
    dbHelper: DbHelper
) : ProfileContract.Presenter {
    private val profileLocalRepo = ProfileLocalRepoImpl(dbHelper)
    private val profileRepository = ProfileRepositoryImpl(profileLocalRepo)
    private val compositeDisposable = CompositeDisposable()

    override fun getHistory() {
        profileRepository.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<History>>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: List<History>) {
                    profileView.showData(t)
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    override fun onViewDetached() {
        compositeDisposable.dispose()
    }
}