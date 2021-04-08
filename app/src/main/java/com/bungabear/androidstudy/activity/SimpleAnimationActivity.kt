package com.bungabear.androidstudy.activity

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.bungabear.androidstudy.R

class SimpleAnimationActivity : AppCompatActivity() {

    lateinit var btnAnimation : Button
    lateinit var clParent : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_animation)
        clParent = findViewById(R.id.cl_parent)
        btnAnimation = findViewById(R.id.btn_animation)
        btnAnimation.setOnClickListener {
            Thread {
                for (i in 0..4) {
                    runOnUiThread { addHeartAnimation(clParent, clParent) }
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }.start()
        }

    }

    private fun addHeartAnimation(parent: ConstraintLayout, targetView: View) {
        val animView = ImageView(this)
        animView.scaleType = ImageView.ScaleType.FIT_CENTER
        animView.setImageResource(R.drawable.iv_vt_heart)
        val viewId = View.generateViewId()
        animView.id = viewId
        val set = ConstraintSet()
        set.clone(parent)
        set.constrainWidth(viewId, parent.width / 5)
        set.setDimensionRatio(viewId, "1:1")
        set.connect(viewId, ConstraintSet.START, targetView.id, ConstraintSet.START)
        set.connect(viewId, ConstraintSet.END, targetView.id, ConstraintSet.END)
        set.connect(viewId, ConstraintSet.TOP, targetView.id, ConstraintSet.TOP)
        set.connect(viewId, ConstraintSet.BOTTOM, targetView.id, ConstraintSet.BOTTOM)
        set.setHorizontalBias(viewId, Math.random().toFloat())
        set.setVerticalBias(viewId, Math.random().toFloat())

        parent.addView(animView)
        set.applyTo(parent)

        val animation = AnimationUtils.loadAnimation(this, R.anim.heart_anim)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                parent.post { parent.removeView(animView) }
            }
        })
        animView.startAnimation(animation)
    }
}