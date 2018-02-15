package com.elpassion.assignment.ui.main

import com.elpassion.assignment.model.ItemList
import com.elpassion.assignment.model.Repository
import com.elpassion.assignment.model.User

/**
 * Created by pavel on 10/2/18.
 */
interface MainPresenter {

    fun getUsers(page: Int,user:String)

    fun getRepos(page: Int,repo:String)

    fun sortList(itemList: List<ItemList>)

    fun convertUsersToList ( users: List<User>)

    fun convertReposToList ( repos: List<Repository>)
}