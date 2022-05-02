package com.pichurchyk.softCorp.ui.splash

import android.os.Bundle
import android.view.View
import com.pichurchyk.softCorp.R
import com.pichurchyk.softCorp.common.base.BaseFragment
import com.pichurchyk.softCorp.common.delegate.viewBinding
import com.pichurchyk.softCorp.databinding.FragmentSplashBinding
import com.pichurchyk.softCorp.ui.splash.util.AnimationListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash), AnimationListener.CompleteListener {
    private val binding: FragmentSplashBinding by viewBinding<FragmentSplashBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.addTransitionListener(AnimationListener(this))
    }

    override fun transitionComplete() {
        navigate(R.id.action_splashFragment_to_wrapperFragment)
    }
}