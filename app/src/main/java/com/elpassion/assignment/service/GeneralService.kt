package com.elpassion.assignment.service

import com.elpassion.assignment.PassionApp
import com.elpassion.assignment.di.components.DaggerNetworkComponent
import com.elpassion.assignment.di.modules.NetworkModule
import com.elpassion.assignment.dto.ResponseDtoRepos
import com.elpassion.assignment.dto.ResponseDtoUser
import com.elpassion.assignment.model.Repository
import com.elpassion.assignment.model.User
import com.elpassion.assignment.network.NetworkService
import com.elpassion.assignment.network.data.NetworkError
import com.elpassion.assignment.utils.Constants
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by pavel on 23/1/18.
 */
class GeneralService : IGeneralService {


    var appComponent = PassionApp.appComponent

    private var subscription: Subscription? = null

    init {
        DaggerNetworkComponent.builder()
                .networkModule(NetworkModule())
                .appComponent(appComponent)
                .build()
                .inject(this)
    }

    @Inject
    lateinit var networkService: NetworkService


    /**
     *  Get searched place
     */
    override fun getUsers(query:String,listener: IGeneralService.OnGetUsersListener){

        subscription = networkService.getUsers(query, Constants.maxRows, Constants.startRow)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { throwable -> Observable.error(throwable) }
                .subscribe(object : Subscriber<ResponseDtoUser<User>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        listener.onFailureUsers(NetworkError(e))
                    }

                    override fun onNext(user: ResponseDtoUser<User>) {
                        listener.onSuccessUsers(user)
                    }
                })

    }

    /**
     *  Get stations of searched place
     */
    override fun getRepos(query: String, listener: IGeneralService.OnGetReposListener){

        subscription = networkService.getRepos(query, Constants.maxRows, Constants.startRow)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { throwable -> Observable.error(throwable) }
                .subscribe(object : Subscriber<ResponseDtoRepos<Repository>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        listener.onFailureRepos(NetworkError(e))
                    }

                    override fun onNext(repos: ResponseDtoRepos<Repository>) {
                        listener.onSuccessRepos(repos)
                    }
                })

    }



    override fun cancelNetworkRequest() {
        subscription?.unsubscribe()
    }

}