package com.elpassion.assignment.service

import com.elpassion.assignment.dto.ResponseDtoRepos
import com.elpassion.assignment.dto.ResponseDtoUser
import com.elpassion.assignment.model.Person
import com.elpassion.assignment.model.Repository
import com.elpassion.assignment.model.Star
import com.elpassion.assignment.model.User
import com.elpassion.assignment.network.data.NetworkError

/**
 * Created by pavel on 24/1/18.
 */
interface IGeneralService {

    interface OnGetUsersListener {
        fun onSuccessUsers(users: ResponseDtoUser<User>)
        fun onFailureUsers(networkError: NetworkError)
    }

    interface OnGetReposListener {
        fun onSuccessRepos(repos: ResponseDtoRepos<Repository>)
        fun onFailureRepos(networkError: NetworkError)
    }

    interface OnGetPersonListener {
        fun onSuccessPerson(person: Person)
        fun onFailurePerson(networkError: NetworkError)
    }

    interface OnGetPersonStarsListener {
        fun onSuccessPersonStars(repos: List<Star>)
        fun onFailurePersonStars(networkError: NetworkError)
    }

    fun getUsers( query:String,listener: OnGetUsersListener)

    fun getRepos( query:String,listener: OnGetReposListener)

    fun getSpecificUser(name:String,listener: OnGetPersonListener)

    fun getStarsOfUser(name: String, listener: OnGetPersonStarsListener)

    fun cancelNetworkRequest()

}