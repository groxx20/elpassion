package com.elpassion.assignment.di.modules

import android.content.Context
import com.elpassion.assignment.PassionApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by pavel on 22/1/18.
 */

@Module
class AppModule (private val mApp: PassionApp){

    @Singleton
    @Provides
    fun provideContext(): Context {
        return mApp
    }

    @Singleton
    @Provides
    fun provideApplication(): PassionApp {
        return mApp
    }

}