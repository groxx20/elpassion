package com.elpassion.assignment.ui.main

import com.elpassion.assignment.model.Repository
import com.elpassion.assignment.model.User

/**
 * Created by pavel on 10/2/18.
 */
interface MainPresenter {

    fun getUsers(user:String)

    fun getRepos(repo:String)

    fun mergeLists(users: List<User>, repos: List<Repository>)

    fun convertUsersToList ( users: List<User>)

    fun convertReposToList ( repos: List<Repository>)
}