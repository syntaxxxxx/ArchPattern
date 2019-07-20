package com.example.archpatternandroid.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.archpatternandroid.R

class SplashActivity : AppCompatActivity(), SplashContract.View {

    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initPresenter()
    }

    private fun initPresenter() {
        presenter = SplashPresenter()
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
