package com.example.archpatternandroid.detailuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.archpatternandroid.R

class DetailActivity : AppCompatActivity(), DetailContract.View {

    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initPresenter()
    }

    private fun initPresenter() {
        presenter = DetailPresenter()
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
