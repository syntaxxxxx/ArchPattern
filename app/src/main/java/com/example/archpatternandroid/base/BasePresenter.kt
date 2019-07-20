package com.example.archpatternandroid.base

interface BasePresenter<T : BaseView> {

    fun onAttach(view: T)
    fun onDettach()
}