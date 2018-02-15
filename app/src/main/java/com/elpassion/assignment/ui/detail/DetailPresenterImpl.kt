package com.elpassion.assignment.ui.detail

import android.util.Log
import com.elpassion.assignment.dto.ResponseDtoWeather
import com.elpassion.assignment.model.Weather
import com.elpassion.assignment.network.data.NetworkError
import com.elpassion.assignment.service.IGeneralService

/**
 * Created by pavel on 10/2/18.
 */

class DetailPresenterImpl(private val iGeneralService: IGeneralService,private val detailView: DetailView) : DetailPresenter, IGeneralService.OnGetInfoListener {


    private val TAG: String = "DetailPresenter"
    /**
     *  Request stations of searched place
     */
    override fun obtainData(south: Double, north:Double , east: Double, west: Double) {
        detailView.showLoading()
        iGeneralService.getInfo(south,north,east,west, this)
    }

    override fun onSuccess(weather: ResponseDtoWeather<Weather>) {

        Log.d(TAG, "success, got some stations to show")

        detailView.hideLoading()

    }

    override fun onFailure(networkError: NetworkError) {

        detailView.hideLoading()
        detailView.onFailure(networkError.appErrorMessage)
        Log.d(TAG, "failure, something went wrong or found 0 stations")
    }


}