package com.example.archpatternandroid.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.archpatternandroid.R

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPresenter()
    }

    private fun initPresenter() {
        presenter = LoginPresenter()
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
