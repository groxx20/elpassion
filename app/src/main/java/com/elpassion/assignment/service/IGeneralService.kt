package com.elpassion.assignment.service

import com.elpassion.assignment.dto.ResponseDto
import com.elpassion.assignment.dto.ResponseDtoWeather
import com.elpassion.assignment.model.Place
import com.elpassion.assignment.model.Weather
import com.elpassion.assignment.network.data.NetworkError

/**
 * Created by pavel on 24/1/18.
 */
interface IGeneralService {

    interface OnGetPlaceListener {
        fun onSuccess(place: ResponseDto<Place>)
        fun onFailure(networkError: NetworkError)
    }

    interface OnGetInfoListener {
        fun onSuccess(weather: ResponseDtoWeather<Weather>)
        fun onFailure(networkError: NetworkError)
    }

    fun getPlaces( name:String,listener: OnGetPlaceListener)

    fun getInfo(south: Double, north:Double , east: Double, west: Double,listener: OnGetInfoListener)

    fun cancelNetworkRequest()

}