package com.elpassion.assignment.di.components

import com.elpassion.assignment.PassionApp
import com.elpassion.assignment.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by pavel on 22/1/18.
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent{


    fun inject(app: PassionApp)

}