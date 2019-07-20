package com.example.archpatternandroid.register

import com.example.archpatternandroid.base.BasePresenter

class RegisterPresenter(var regisView: RegisterContract.View? = null) :BasePresenter<RegisterContract.View>{

    override fun onAttach(view: RegisterContract.View) {
        regisView = view
    }

    override fun onDettach() {
        regisView = null
    }
}