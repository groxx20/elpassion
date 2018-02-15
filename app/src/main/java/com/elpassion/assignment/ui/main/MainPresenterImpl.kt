package com.elpassion.assignment.ui.main

import android.util.Log
import com.elpassion.assignment.dto.ResponseDtoRepos
import com.elpassion.assignment.dto.ResponseDtoUser
import com.elpassion.assignment.model.ItemList
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


    /**
     *  Get users from Endpoint
     */
    override fun getUsers(page: Int, user: String) {

        mainView.showLoading()
        iGeneralService.getUsers(user,page, this)
    }

    /**
     *  Get repos from Endpoint
     */
    override fun getRepos(page: Int, repo: String) {

        mainView.showLoading()
        iGeneralService.getRepos(repo,page, this)
    }

    override fun onSuccessUsers(users: ResponseDtoUser<User>) {

        Log.d(TAG, "got users succesfully")
        convertUsersToList(users.items!!)
        mainView.hideLoading()
    }

    override fun onFailureUsers(networkError: NetworkError) {

        Log.d(TAG, "failed retrieving users")
        mainView.hideLoading()
        iGeneralService.cancelNetworkRequest()
    }

    override fun onSuccessRepos(repos: ResponseDtoRepos<Repository>) {

        Log.d(TAG, "got users succesfully")
        convertReposToList(repos.items!!)
        mainView.hideLoading()
    }

    override fun onFailureRepos(networkError: NetworkError) {

        Log.d(TAG, "failed retrieving repos")
        mainView.hideLoading()
        iGeneralService.cancelNetworkRequest()
    }



    /**
     *  Convert User to List Item
     */
    override fun convertUsersToList(users: List<User>){

        users.mapTo(items) { ItemList(it.id, it.login, "", it.avatar_url, true) }

        mainView.getItemsRepos()

    }

    /**
     *  Convert Repo to List Item
     */
    override fun convertReposToList(repos: List<Repository>) {

        repos.mapTo(items) { ItemList(it.id, it.owner.login, it.name, it.owner.avatar_url, false) }
        mainView.readyToSortItems(items)

    }

    /**
     *  Sort List by ID
     */
    override fun sortList(itemList: List<ItemList>) {

        val sortedList = itemList.sortedWith(compareBy({ it.id }))
        mainView.showStuff(sortedList)
        iGeneralService.cancelNetworkRequest()
    }





}