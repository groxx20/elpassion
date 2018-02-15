package com.elpassion.assignment

import android.app.Application

import com.elpassion.assignment.di.components.AppComponent
import com.elpassion.assignment.di.modules.AppModule
import com.elpassion.assignment.di.components.DaggerAppComponent

/**
 * Created by pavel on 22/1/18.
 */


class PassionApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

    }

}