package com.example.archpatternandroid.splash

import com.example.archpatternandroid.base.BasePresenter

class SplashPresenter(var splashView: SplashContract.View? = null) : BasePresenter<SplashContract.View> {

    override fun onAttach(view: SplashContract.View) {
        splashView = view
    }

    override fun onDettach() {
        splashView = null
    }
}