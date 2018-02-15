package com.elpassion.assignment.ui.detail

import android.util.Log
import com.elpassion.assignment.model.Person
import com.elpassion.assignment.model.Star
import com.elpassion.assignment.network.data.NetworkError
import com.elpassion.assignment.service.IGeneralService

/**
 * Created by pavel on 10/2/18.
 */

class DetailPresenterImpl(private val iGeneralService: IGeneralService,private val detailView: DetailView) : DetailPresenter, IGeneralService.OnGetPersonListener, IGeneralService.OnGetPersonStarsListener {



    private val TAG: String = "DetailPresenter"


    override fun obtainUser(name: String) {
        detailView.showLoading()
        iGeneralService.getSpecificUser(name,this)
    }

    override fun obtainStars(name: String) {

        detailView.showLoading()
        iGeneralService.getStarsOfUser(name, this)
    }


    override fun onSuccessPerson(person: Person) {

        Log.d(TAG,"got the user successfully")
        detailView.hideLoading()
        detailView.requestStars()
    }

    override fun onFailurePerson(networkError: NetworkError) {

        Log.d(TAG,"failed retrieving the user")
        detailView.hideLoading()
    }

    override fun onSuccessPersonStars(repos: List<Star>) {
        Log.d(TAG,"got user stars successfully")
        detailView.hideLoading()
    }

    override fun onFailurePersonStars(networkError: NetworkError) {

        Log.d(TAG,"failed retrieving stars of the user")
        detailView.hideLoading()
    }
}