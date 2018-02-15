package com.elpassion.assignment.service

import com.elpassion.assignment.PassionApp
import com.elpassion.assignment.network.data.NetworkError
import com.elpassion.assignment.di.components.DaggerNetworkComponent
import com.elpassion.assignment.di.modules.NetworkModule
import com.elpassion.assignment.dto.ResponseDto
import com.elpassion.assignment.dto.ResponseDtoWeather
import com.elpassion.assignment.model.Place
import com.elpassion.assignment.model.Weather
import com.elpassion.assignment.network.NetworkService
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
    override fun getPlaces(name:String,listener: IGeneralService.OnGetPlaceListener){

        subscription = networkService.getPlace(name, Constants.maxRows, Constants.startRow, Constants.lang, Constants.isNameRequired, Constants.style, Constants.username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { throwable -> Observable.error(throwable) }
                .subscribe(object : Subscriber<ResponseDto<Place>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        listener.onFailure(NetworkError(e))
                    }

                    override fun onNext(place: ResponseDto<Place>) {
                        listener.onSuccess(place)
                    }
                })

    }

    /**
     *  Get stations of searched place
     */
    override fun getInfo(south: Double, north:Double , east: Double, west: Double, listener: IGeneralService.OnGetInfoListener){

        subscription = networkService.getInfo(north,south,east,west,Constants.username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { throwable -> Observable.error(throwable) }
                .subscribe(object : Subscriber<ResponseDtoWeather<Weather>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        listener.onFailure(NetworkError(e))
                    }

                    override fun onNext(weather: ResponseDtoWeather<Weather>) {
                        listener.onSuccess(weather)
                    }
                })

    }



    override fun cancelNetworkRequest() {
        subscription?.unsubscribe()
    }

}