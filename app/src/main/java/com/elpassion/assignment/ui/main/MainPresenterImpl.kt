package com.elpassion.assignment.ui.main

import com.elpassion.assignment.dto.ResponseDtoRepos
import com.elpassion.assignment.dto.ResponseDtoUser
import com.elpassion.assignment.model.ItemList
import com.elpassion.assignment.model.Place
import com.elpassion.assignment.model.Repository
import com.elpassion.assignment.model.User
import com.elpassion.assignment.network.data.NetworkError
import com.elpassion.assignment.service.IGeneralService

/**
 * Created by pavel on 24/1/18.
 */

class MainPresenterImpl(private val iGeneralService: IGeneralService, private val mainView: MainView) : IGeneralService.OnGetUsersListener, IGeneralService.OnGetReposListener, MainPresenter{



    private val TAG: String = "MainPresenter"
    private val items: ArrayList<ItemList> = ArrayList()


    override fun getUsers(user: String) {

        iGeneralService.getUsers(user, this)
    }

    override fun getRepos(repo: String) {

        iGeneralService.getRepos(repo, this)
    }


    /*override fun onSuccess(user: ResponseDtoUser<Place>) {

        Log.d(TAG, "success, got the place")
        if(place.geonames != null && place.geonames!!.isNotEmpty()) {
            convertType(place.geonames!![0])
        }else{
            mainView.onFailure("nothing found")
        }
        mainView.hideLoading()


    }

    override fun onFailure(networkError: NetworkError) {
        Log.d(TAG, "failure, something went wrong")
        mainView.onFailure(networkError.appErrorMessage)
        mainView.hideLoading()
    } */

    override fun onSuccessUsers(users: ResponseDtoUser<User>) {

    }

    override fun onFailureUsers(networkError: NetworkError) {

    }

    override fun onSuccessRepos(repos: ResponseDtoRepos<Repository>) {

    }

    override fun onFailureRepos(networkError: NetworkError) {

    }



    private fun convertType(place: Place){


        mainView.goNext(place.name)

    }



    override fun mergeLists(users: List<User>, repos: List<Repository>) {

    }


}