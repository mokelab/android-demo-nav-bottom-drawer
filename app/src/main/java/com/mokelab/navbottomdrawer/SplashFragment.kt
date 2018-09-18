package com.mokelab.navbottomdrawer

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class SplashFragment : Fragment() {
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.handler = Handler()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity) ?.drawerEnabled = false
        (activity as? MainActivity) ?.appBarVisible = false
        (activity as? MainActivity) ?.bottomNavVisible = false
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed({
            Navigation.findNavController(activity!!, R.id.container)
                    .navigate(R.id.action_splashFragment_to_homeFragment)
        }, 1000)
    }
}