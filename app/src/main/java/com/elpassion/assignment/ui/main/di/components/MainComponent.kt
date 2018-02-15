package com.elpassion.assignment.ui.main.di.components

import com.elpassion.assignment.di.components.AppComponent
import com.elpassion.assignment.ui.main.MainActivity
import com.elpassion.assignment.ui.main.di.modules.MainModule
import com.elpassion.assignment.ui.main.di.scopes.MainScope
import dagger.Component

/**
 * Created by pavel on 25/1/18.
 */
@MainScope
@Component(dependencies = [AppComponent::class], modules = [MainModule::class])
interface MainComponent{

    fun inject(mainActivity: MainActivity)
}