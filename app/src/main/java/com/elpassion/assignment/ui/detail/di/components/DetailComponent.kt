package com.elpassion.assignment.ui.main.di.components

import com.elpassion.assignment.di.components.AppComponent
import com.elpassion.assignment.ui.detail.DetailActivity
import com.elpassion.assignment.ui.main.di.modules.DetailActivityModule
import com.elpassion.assignment.ui.main.di.scopes.DetailScope
import dagger.Component

/**
 * Created by pavel on 25/1/18.
 */
@DetailScope
@Component(dependencies = [AppComponent::class], modules = [DetailActivityModule::class])
interface DetailComponent{

    fun inject(detailActivity: DetailActivity)
}