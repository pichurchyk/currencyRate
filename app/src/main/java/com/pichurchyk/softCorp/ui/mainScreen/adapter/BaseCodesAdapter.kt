package com.pichurchyk.softCorp.ui.mainScreen.adapter

import android.view.View
import android.widget.AdapterView

class BaseCodesAdapter(private val itemClick: ItemClick) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parentView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        itemClick.changeCode(parentView, position)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    interface ItemClick {
        fun changeCode(parentView: AdapterView<*>?, position: Int)
    }
}