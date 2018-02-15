package com.elpassion.assignment.ui.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.elpassion.assignment.PassionApp
import com.elpassion.assignment.R
import com.elpassion.assignment.ui.main.di.components.DaggerDetailComponent
import com.elpassion.assignment.ui.main.di.components.DetailComponent
import com.elpassion.assignment.ui.main.di.modules.DetailActivityModule
import com.elpassion.assignment.utils.loadImg
import com.elpassion.assignment.utils.toastError
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject


@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity(), DetailView {


    private lateinit var detailComponent: DetailComponent

    @Inject
    lateinit var detailPresenter: DetailPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        injectDependecies()

        clicks()

        detailPresenter.obtainUser(intent.getStringExtra("name"))



    }

    /**
     *  Inject dependencies of that ui part
     */
    private fun injectDependecies(){
        val appComponent = PassionApp.appComponent

        detailComponent = DaggerDetailComponent.builder()
                .detailActivityModule(DetailActivityModule(this))
                .appComponent(appComponent)
                .build()
        detailComponent.inject(this)
    }

    /**
     *  On clicks methods
     */
    private fun clicks(){
        exitImg.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out)
        }
    }


    /**
     *  Request count of Stars
     */
    override fun requestStars() { detailPresenter.obtainStars(intent.getStringExtra("name")) }

    /**
     *  Fill starsTxt
     */
    override fun drawStars(stars: Int) { starsTxt.text = stars.toString() }

    /**
     *  Fill fields with obtained information
     */
    override fun drawInfo(url: String, followers: Int) {

        avatarImg.loadImg(url)
        followersTxt.text = followers.toString()
        nameTxt.text = intent.getStringExtra("name")
    }



    override fun hideLoading() {

        progressBarDetail.visibility = View.INVISIBLE
    }

    override fun showLoading() {

        progressBarDetail.visibility = View.VISIBLE
    }

    override fun onFailure(msg: String) {

        toastError(msg)
    }

}
