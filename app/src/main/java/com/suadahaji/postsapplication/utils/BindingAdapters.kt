package com.suadahaji.postsapplication.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.*

@BindingAdapter("capitalizeSentence")
fun TextView.capitalizeSentence(title: String) {
    text = title.split(' ').joinToString(" ") { it.capitalize(Locale.getDefault()) }
}