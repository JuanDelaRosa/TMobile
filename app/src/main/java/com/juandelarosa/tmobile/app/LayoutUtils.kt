package com.juandelarosa.tmobile.app

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.juandelarosa.domain.entities.TextStyle
import com.juandelarosa.tmobile.R


class LayoutUtils {
    companion object {

        fun textWithStyle(view : TextView, style: TextStyle){
            view.text = style.text
            view.textSize = style.size
            view.setTextColor(Color.parseColor(style.color))
        }
        fun setImage(view: ImageView, url :String){
            Glide.with(view.context).load(url).into(view)
        }
        fun removeSplashScreen(view: ImageView){
            val height = (80 * view.context.resources.displayMetrics.density)
            val animation = ShowAnimation(view,height)
            animation.duration = 600
            view.startAnimation(animation)
        }

        fun showSnack(view: View, it: String) {
            val snackbar = Snackbar.make(view, it, Snackbar.LENGTH_SHORT)
            snackbar.setTextColor(ResourcesCompat.getColor(view.context.resources, R.color.colorPrimary,null))
            snackbar.show()
        }
    }
}

@BindingAdapter("url")
fun bindLoadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url).into(view)
    }
}