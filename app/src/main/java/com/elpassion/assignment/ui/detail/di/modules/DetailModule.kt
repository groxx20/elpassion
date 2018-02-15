package com.elpassion.assignment.ui.main.di.modules

import com.elpassion.assignment.service.GeneralService
import com.elpassion.assignment.service.IGeneralService
import com.elpassion.assignment.ui.detail.DetailPresenterImpl
import com.elpassion.assignment.ui.detail.DetailView
import com.elpassion.assignment.ui.main.di.scopes.DetailScope
import dagger.Module
import dagger.Provides

/**
 * Created by pavel on 25/1/18.
 */

@Module
class DetailActivityModule ( private val detailView: DetailView) {

    @Provides
    @DetailScope
    fun providesView(): DetailView {
        return  detailView
    }

    @Provides
    @DetailScope
    fun provideGeneralService(): IGeneralService {
        return GeneralService()
    }


    @Provides
    @DetailScope
    fun providesDetailPresenter(iGeneralService: IGeneralService, detailView: DetailView) : DetailPresenterImpl {
        return DetailPresenterImpl(iGeneralService, detailView)
    }
}
