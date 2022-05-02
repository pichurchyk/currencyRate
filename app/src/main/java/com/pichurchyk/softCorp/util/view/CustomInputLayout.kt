package com.pichurchyk.softCorp.util.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.pichurchyk.softCorp.R

class CustomInputLayout(
    context: Context,
    attrs: AttributeSet,
) : LinearLayout(context, attrs) {

    private var layoutHeader: TextView? = null
    private var input: EditText? = null

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        orientation = VERTICAL

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_input_layout, this, true)
        val typedArr = context.obtainStyledAttributes(attrs, R.styleable.CustomInputLayout)

        layoutHeader = view.findViewById(R.id.header)
        input = view.findViewById(R.id.input)

        try {
            val header = typedArr.getString(R.styleable.CustomInputLayout_inputHeader)
            val hint = typedArr.getString(R.styleable.CustomInputLayout_inputHint)

            input?.hint = hint
            layoutHeader?.text = header
        } catch (e: Exception) {
            Log.e("Exception", e.message.toString())
        } finally {
            typedArr.recycle()
        }
    }
}