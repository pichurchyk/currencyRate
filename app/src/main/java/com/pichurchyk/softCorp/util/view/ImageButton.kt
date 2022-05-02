package com.pichurchyk.softCorp.util.view

import android.widget.ImageButton
import androidx.core.content.ContextCompat

fun ImageButton.changeDrawable(drawable: Int) {
    this.setImageDrawable(
        ContextCompat.getDrawable(
            this.context,
            drawable
        )
    )
}