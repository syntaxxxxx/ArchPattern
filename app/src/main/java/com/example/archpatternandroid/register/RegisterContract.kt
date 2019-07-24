package com.example.archpatternandroid.register

import com.example.archpatternandroid.base.BaseView

interface RegisterContract {

    interface Presenter {
        fun regis()
    }

    interface View : BaseView {

    }
}