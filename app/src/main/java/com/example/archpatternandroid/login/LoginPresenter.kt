package com.example.archpatternandroid.login

import com.example.archpatternandroid.base.BasePresenter

class LoginPresenter(var loginView: LoginContract.View? = null) : BasePresenter<LoginContract.View> {

    override fun onAttach(view: LoginContract.View) {
        loginView = view
    }

    override fun onDettach() {
        loginView = null
    }
}