package com.pichurchyk.softCorp.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pichurchyk.softCorp.util.view.snackbar

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    fun snackbar(view: View, messageString: String) = requireContext().snackbar(view, messageString)

    fun navigate(@IdRes resId: Int) = findNavController().navigate(resId)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnClickListener {
            requireView().clearFocus()
        }
    }
}