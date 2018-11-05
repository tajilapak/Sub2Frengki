package com.example.frengki.sub2frengki.layout

import android.support.design.widget.AppBarLayout
import android.view.View
import android.widget.FrameLayout
import com.example.frengki.sub2frengki.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout

class MainActivityUi : AnkoComponent<MainActivity> {

    lateinit var frameLayout: FrameLayout

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        coordinatorLayout {

            val appBarLayout = appBarLayout {
                id = View.generateViewId()
                lparams(width = matchParent, height = wrapContent)
                toolbar {
                    lparams(width = matchParent, height = wrapContent) {

                    }

                }
            }
            linearLayout {

            }.lparams {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            frameLayout = frameLayout{
                id = View.generateViewId()
            }.lparams {
                width = matchParent

            }

        }
    }
}