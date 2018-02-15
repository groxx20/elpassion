package com.elpassion.assignment.ui.main

import com.elpassion.assignment.model.Place

/**
 * Created by pavel on 5/2/18.
 */
interface MainView {

    fun showLoading()

    fun hideLoading()

    fun showStuff(places: List<Place>)

    fun onFailure(msg:String)

    fun goNext(city:String)

}