package com.example.archpatternandroid.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.archpatternandroid.R

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfileFragment : Fragment(), ProfileContract.View {

    lateinit var presenter: ProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun initPresenter() {
        presenter = ProfilePresenter()
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }
}
