package com.pichurchyk.softCorp.ui.mainScreen.wrapper.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pichurchyk.softCorp.ui.mainScreen.currenciesList.CurrenciesListFragment
import com.pichurchyk.softCorp.ui.mainScreen.likedCurrencies.LikedCurrenciesFragment

class WrapperAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) CurrenciesListFragment() else LikedCurrenciesFragment()
    }
}