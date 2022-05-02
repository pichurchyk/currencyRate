package com.pichurchyk.softCorp.ui.mainScreen.wrapper

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.pichurchyk.softCorp.R
import com.pichurchyk.softCorp.common.base.BaseFragment
import com.pichurchyk.softCorp.common.delegate.viewBinding
import com.pichurchyk.softCorp.databinding.FragmentWrapperBinding
import com.pichurchyk.softCorp.ui.mainScreen.wrapper.adapter.WrapperAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WrapperFragment : BaseFragment(R.layout.fragment_wrapper) {
    private val binding by viewBinding<FragmentWrapperBinding>()

    private val viewPagerItemsNames = arrayOf("All currencies", "Favorite currencies")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabLayout()
    }

    private fun setupTabLayout() {
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager
        viewPager.adapter = WrapperAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = viewPagerItemsNames[position]
        }.attach()
    }
}