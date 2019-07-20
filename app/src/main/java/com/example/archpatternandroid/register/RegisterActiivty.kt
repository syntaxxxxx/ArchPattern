package com.example.archpatternandroid.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.archpatternandroid.R

class RegisterActiivty : AppCompatActivity(), RegisterContract.View {

    lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_actiivty)

        initPresenter()
    }

    private fun initPresenter() {
        presenter = RegisterPresenter()
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }
}
