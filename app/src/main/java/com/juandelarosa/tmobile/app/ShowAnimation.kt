package com.juandelarosa.tmobile.app

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation


class ShowAnimation(private val view: View, private var targetHeight: Float) : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            view.layoutParams.height = (targetHeight / interpolatedTime).toInt()
            view.requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
}