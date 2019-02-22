package com.google.codelabs.mdc.kotlin.shipping

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.shipping_info_activity.*


class ShippingInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shipping_info_activity)

        val rootView = findViewById<View>(android.R.id.content)

        val textInputLayouts = Utils.findViewsWithType(rootView, TextInputLayout::class.java)

        var noErrors = true

        for (textInputLayout in textInputLayouts) {
            textInputLayout.getInputEditText()?.addTextChangedListener(object : BdTextWatcher() {
                override fun validate(text: String) {
                    if (text.length > 5) {
                        textInputLayout.error = "too large please respect the rules :)"
                        noErrors = false
                    } else {
                        textInputLayout.error = null
                        noErrors = true
                    }
                }
            })
        }

        save_button.setOnClickListener {
            for (textInputLayout in textInputLayouts) {
                val editTextString = textInputLayout.editText!!.text.toString()
                if (editTextString.isEmpty()) {
                    textInputLayout.error = resources.getString(R.string.error_string)
                    noErrors = false
                } else {
                    textInputLayout.error = null
                    noErrors = true
                }
            }
            if (noErrors) Snackbar.make(rootView, "OK", Snackbar.LENGTH_LONG).show()
        }
    }

    abstract class BdTextWatcher : TextWatcher {
        abstract fun validate(text: String)
        override fun afterTextChanged(editable: Editable) = validate(editable.toString())
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    }
}
