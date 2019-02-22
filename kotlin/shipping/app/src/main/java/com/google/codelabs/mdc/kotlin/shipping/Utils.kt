package com.google.codelabs.mdc.kotlin.shipping

import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

import java.util.ArrayList

/**
 * Utils class used in MDC-111 application.
 */
object Utils {

    fun <T : View> findViewsWithType(root: View, type: Class<T>): List<T> {
        val views = ArrayList<T>()
        findViewsWithType(root, type, views)
        return views
    }

    private fun <T : View> findViewsWithType(view: View, type: Class<T>, views: MutableList<T>) {
        if (type.isInstance(view)) {
            views.add(type.cast(view))
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                findViewsWithType(view.getChildAt(i), type, views)
            }
        }
    }
}

fun TextInputLayout.getInputEditText(): TextInputEditText? {
    for (i in 0 until childCount) {
        val childView = getChildAt(i)
        return (childView as? FrameLayout)?.getChildAt(0) as? TextInputEditText
    }
    return null
}
