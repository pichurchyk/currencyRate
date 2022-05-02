package com.pichurchyk.softCorp.util.view

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.pichurchyk.softCorp.R

fun Context.snackbar(view: View, messageString: String) {
    if (view.isAttachedToWindow) {
        val color = R.color.colorPrimary
        val secondColor = R.color.colorPrimaryDark
        val snackbar = Snackbar.make(view, messageString, Snackbar.LENGTH_INDEFINITE)
            .setActionTextColor(ContextCompat.getColor(applicationContext, secondColor))
            .setAction(getString(R.string.close)) { }
            .setTextColor(ContextCompat.getColor(applicationContext, secondColor))
        snackbar.view.setBackgroundColor(ContextCompat.getColor(applicationContext, color))
        snackbar.show()
    }
}