package de.fluchtwege.untitled.common

import android.databinding.BindingAdapter
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView


@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    if (visible) {
        view.setVisibility(View.VISIBLE)
    } else {
        view.setVisibility(View.GONE)
    }
}

@BindingAdapter("textColorResource")
fun setTextColorResource(textView: TextView, @ColorRes color: Int) {
    textView.setTextColor(ContextCompat.getColor(textView.context, color))
}