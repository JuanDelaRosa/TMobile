package com.juandelarosa.tmobile.app

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.juandelarosa.data.Exceptions
import com.juandelarosa.domain.entities.TextStyle
import com.juandelarosa.tmobile.R
import kotlin.system.exitProcess

//Class dedicated to the interaction with the view
class LayoutUtils {
    companion object {

        //Set the font size, color and text of a textvie
        fun textWithStyle(view : TextView, style: TextStyle){
            view.text = style.text
            view.textSize = style.size
            view.setTextColor(Color.parseColor(style.color))
        }
        //Glide library is used to load the image
        fun setImage(view: ImageView, url :String?){
            if (!url.isNullOrEmpty()) {
                Glide.with(view.context).load(url).into(view)
            }
        }
        //Splashscreen animation
        fun removeSplashScreen(view: ImageView){
            val height = (80 * view.context.resources.displayMetrics.density)
            val animation = ShowAnimation(view,height)
            animation.duration = 600
            view.startAnimation(animation)
        }
        //Show information box on the main screen
        fun showSnack(view: View, message: String) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            snackbar.setTextColor(ResourcesCompat.getColor(view.context.resources, R.color.colorPrimary,null))
            if(message == Exceptions.NoBackup) {
                snackbar.setAction("Exit") { exitProcess(0) }
                snackbar.duration = Snackbar.LENGTH_LONG
            }
            snackbar.show()
        }
    }
}

//Load the image in InfoActivity
@BindingAdapter("url")
fun bindLoadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url).into(view)
    }
}