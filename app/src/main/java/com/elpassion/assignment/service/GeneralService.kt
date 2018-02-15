package com.elpassion.assignment.service

import com.elpassion.assignment.PassionApp
import com.elpassion.assignment.di.components.DaggerNetworkComponent
import com.elpassion.assignment.di.modules.NetworkModule
import com.elpassion.assignment.dto.ResponseDtoRepos
import com.elpassion.assignment.dto.ResponseDtoUser
import com.elpassion.assignment.model.Person
import com.elpassion.assignment.model.Repository
import com.elpassion.assignment.model.Star
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
    override fun getUsers(query:String,page:Int,listener: IGeneralService.OnGetUsersListener){

        subscription = networkService.getUsers(query, page, Constants.maxRows)
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
    override fun getRepos(query: String,page: Int,  listener: IGeneralService.OnGetReposListener){

        subscription = networkService.getRepos(query, page, Constants.maxRows)
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

    /**
     *  Get stations of searched place
     */
    override fun getSpecificUser(name: String, listener: IGeneralService.OnGetPersonListener){

        subscription = networkService.getSpecificPerson(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { throwable -> Observable.error(throwable) }
                .subscribe(object : Subscriber<Person>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        listener.onFailurePerson(NetworkError(e))
                    }

                    override fun onNext(person: Person) {
                        listener.onSuccessPerson(person)
                    }
                })

    }

    override fun getStarsOfUser(name: String, listener: IGeneralService.OnGetPersonStarsListener){

        subscription = networkService.getSpecificPersonStars(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { throwable -> Observable.error(throwable) }
                .subscribe(object : Subscriber<List<Star>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        listener.onFailurePersonStars(NetworkError(e))
                    }

                    override fun onNext(stars: List<Star>) {
                        listener.onSuccessPersonStars(stars)
                    }
                })

    }



    override fun cancelNetworkRequest() {
        subscription?.unsubscribe()
    }

}