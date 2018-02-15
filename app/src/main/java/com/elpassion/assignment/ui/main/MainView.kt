package com.elpassion.assignment.ui.main

import com.elpassion.assignment.model.ItemList

/**
 * Created by pavel on 5/2/18.
 */
interface MainView {

    fun showLoading()

    fun hideLoading()

    fun getItemsRepos()

    fun readyToSortItems(items: List<ItemList>)

    fun onFailure(msg:String)

    fun goNext(name:String)

    fun showStuff(items : List<ItemList>)

    fun fetchImages(items: List<ItemList>)

}