package com.example.frengki.sub2frengki.layout

import android.view.View
import com.example.frengki.sub2frengki.fragment.FragmentDetail
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.wrapContent

class MatchDetailUi: AnkoComponent<FragmentDetail> {
    override fun createView(ui: AnkoContext<FragmentDetail>): View = with(ui) {
        coordinatorLayout {
            appBarLayout {
                lparams(width = matchParent, height = wrapContent)
                toolbar {
                    lparams(width = matchParent, height = wrapContent)

                }
            }
        }
    }

}