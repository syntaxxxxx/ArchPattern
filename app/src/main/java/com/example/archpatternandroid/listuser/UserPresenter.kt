package com.example.archpatternandroid.listuser

import com.example.archpatternandroid.base.BasePresenter

class UserPresenter(var userView: UserContract.View? = null) : BasePresenter<UserContract.View> {

    override fun onAttach(view: UserContract.View) {
        userView = view
    }

    override fun onDettach() {
        userView = null
    }
}