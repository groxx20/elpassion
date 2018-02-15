package com.elpassion.assignment.ui.detail

import com.elpassion.assignment.dto.ResponseDtoRepos
import com.elpassion.assignment.model.Repository
import com.elpassion.assignment.network.data.NetworkError
import com.elpassion.assignment.service.IGeneralService

/**
 * Created by pavel on 10/2/18.
 */

class DetailPresenterImpl(private val iGeneralService: IGeneralService,private val detailView: DetailView) : DetailPresenter, IGeneralService.OnGetPersonListener {



    private val TAG: String = "DetailPresenter"
    /**
     *  Request stations of searched place
     */


    override fun obtainUser() {
        detailView.showLoading()
    }

    override fun obtainStars() {

    }


    override fun onSuccessPerson(repos: ResponseDtoRepos<Repository>) {

    }

    override fun onFailurePerson(networkError: NetworkError) {

    }


}