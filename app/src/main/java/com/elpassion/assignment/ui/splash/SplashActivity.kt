package com.elpassion.assignment.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.elpassion.assignment.PassionApp
import com.elpassion.assignment.R
import com.elpassion.assignment.ui.main.MainActivity
import com.elpassion.assignment.ui.splash.di.components.DaggerSplashComponent
import com.elpassion.assignment.ui.splash.di.components.SplashComponent
import com.elpassion.assignment.ui.splash.di.modules.SplashModule
import com.elpassion.assignment.utils.goToActivity
import kotlinx.android.synthetic.main.content_splash.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashView {



    private lateinit var splashComponent: SplashComponent

    @Inject
    lateinit var splashPresenterImpl: SplashPresenterImpl


    private var animation: Animation? = null

    private var countDownTimer: CountDownTimer? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        injectDependencies()
        showAnimation()
    }



    private fun injectDependencies(){

        val appComponent = PassionApp.appComponent

        splashComponent = DaggerSplashComponent.builder()
                .splashModule(SplashModule(this))
                .appComponent(appComponent)
                .build()
        splashComponent.inject(this)
    }

    private fun showAnimation(){

        animation = AnimationUtils.loadAnimation(this, R.anim.start_animation)


        imageLogo.startAnimation(animation)



        countDownTimer = object : CountDownTimer(2500,1500){
            override fun onFinish() {

                progressBar.visibility = View.VISIBLE
                splashPresenterImpl.startProcess()

            }
            override fun onTick(p0: Long) {

            }
        }.start()

    }


    override fun goNext() {

        goToActivity<MainActivity>()
        finish()
    }

    override fun hideLoading() {

        progressBar.visibility = View.INVISIBLE
    }

}
