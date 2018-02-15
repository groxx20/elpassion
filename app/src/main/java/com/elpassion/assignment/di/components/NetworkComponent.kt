package com.elpassion.assignment.di.components

import com.elpassion.assignment.di.modules.NetworkModule
import com.elpassion.assignment.di.scopes.PerService
import com.elpassion.assignment.service.GeneralService
import dagger.Component

/**
 * Created by pavel on 24/1/18.
 */
@PerService
@Component(dependencies = [AppComponent::class], modules = [NetworkModule::class])
interface NetworkComponent{

    fun inject(generalService: GeneralService)
}