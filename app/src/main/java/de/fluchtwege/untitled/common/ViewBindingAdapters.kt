package de.fluchtwege.untitled.common

import android.databinding.BindingAdapter
import android.view.View


@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    if (visible) {
        view.setVisibility(View.VISIBLE)
    } else {
        view.setVisibility(View.GONE)
    }
}