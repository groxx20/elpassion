package com.elpassion.assignment.ui.main

import com.elpassion.assignment.model.ItemList

/**
 * Created by pavel on 5/2/18.
 */
interface MainView {

    fun showLoading()

    fun hideLoading()

    fun getItems()

    fun readyToMergeItems()

    fun onFailure(msg:String)

    fun goNext(city:String)

    fun showStuff(items : List<ItemList>)

}