package com.elpassion.assignment.ui.detail

/**
 * Created by pavel on 10/2/18.
 */
interface DetailView {


    fun showLoading()

    fun hideLoading()

    fun onFailure(msg:String)

}