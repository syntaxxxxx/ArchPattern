package com.example.archpatternandroid.profile

import com.example.archpatternandroid.base.BasePresenter

class ProfilePresenter(var profileView: ProfileContract.View? = null) : BasePresenter<ProfileContract.View> {

    override fun onAttach(view: ProfileContract.View) {
        profileView = view
    }

    override fun onDettach() {
        profileView = null
    }
}