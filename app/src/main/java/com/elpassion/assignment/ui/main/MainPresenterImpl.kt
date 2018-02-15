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


    override fun getUsers(user: String) {

        iGeneralService.getUsers(user, this)
    }

    override fun getRepos(repo: String) {

        iGeneralService.getRepos(repo, this)
    }

    override fun onSuccessUsers(users: ResponseDtoUser<User>) {

        Log.d(TAG, "got users succesfully")
        convertUsersToList(users.items!!)
    }

    override fun onFailureUsers(networkError: NetworkError) {

        Log.d(TAG, "failed retrieving users")
        mainView.onFailure(networkError.appErrorMessage)
        mainView.hideLoading()
    }

    override fun onSuccessRepos(repos: ResponseDtoRepos<Repository>) {

        Log.d(TAG, "got users succesfully")
        convertReposToList(repos.items!!)
    }

    override fun onFailureRepos(networkError: NetworkError) {

        Log.d(TAG, "failed retrieving repos")
        mainView.onFailure(networkError.appErrorMessage)
        mainView.hideLoading()
    }



    override fun convertUsersToList(users: List<User>){

        for (user: User in users){

            val itemListU = ItemList(user.id,user.login, "", user.avatar_url, true)
            items.add(itemListU)
        }

    }

    override fun convertReposToList(repos: List<Repository>) {

        for (repo: Repository in repos){

            val itemListR = ItemList(repo.id,repo.owner.login, repo.name, repo.owner.avatar_url, false)
            items.add(itemListR)
        }


    }



    override fun mergeLists(users: List<User>, repos: List<Repository>) {

    }


}