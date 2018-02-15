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
    override fun obtainData(south: Double, north:Double , east: Double, west: Double) {
        detailView.showLoading()
        //iGeneralService.getInfo(south,north,east,west, this)
    }


    override fun onSuccessPerson(repos: ResponseDtoRepos<Repository>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailurePerson(networkError: NetworkError) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}