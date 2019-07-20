package com.example.archpatternandroid.detailuser

import com.example.archpatternandroid.base.BasePresenter

class DetailPresenter(var detailView: DetailContract.View? = null) : BasePresenter<DetailContract.View> {

    override fun onAttach(view: DetailContract.View) {
        detailView = view
    }

    override fun onDettach() {
        detailView = null
    }
}