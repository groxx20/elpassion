package com.elpassion.assignment.ui.splash.di.components

import com.elpassion.assignment.di.components.AppComponent
import com.elpassion.assignment.ui.splash.SplashActivity
import com.elpassion.assignment.ui.splash.di.modules.SplashModule
import com.elpassion.assignment.ui.splash.di.scopes.SplashScope
import dagger.Component

/**
 * Created by pavel on 5/2/18.
 */
@SplashScope
@Component(dependencies = [AppComponent::class], modules = [SplashModule::class])
interface SplashComponent{

    fun inject(splashActivity: SplashActivity)
}