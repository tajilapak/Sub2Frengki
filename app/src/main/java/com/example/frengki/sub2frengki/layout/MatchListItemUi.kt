package com.example.frengki.sub2frengki.layout

import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.frengki.sub2frengki.R
import org.jetbrains.anko.*

class MatchListItemUi : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            padding = dip(5)
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL

            background = with(TypedValue()) {
                context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
                context.getDrawable(resourceId)
            }

            textView {
                id = Id.eventDate
                textSize = 14f
            }.lparams{

            }

            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(3)
                orientation = LinearLayout.HORIZONTAL
                weightSum = 3F

                textView {
                    id = Id.homeTeamName
                    textSize = 16f
                    gravity = Gravity.RIGHT
                    textColorResource = android.R.color.black
                    ellipsize = TextUtils.TruncateAt.END
                    singleLine = true
                    width = 0
                    setPadding(0,0,dip(10),0)
                }.lparams{
                    topMargin = dip(5)
                    weight = 1.35F
                }

                textView {
                    id = Id.homeTeamScore
                    textSize = 16f
                    textColorResource = android.R.color.black
                }.lparams{
                    topMargin = dip(5)
                    weight = 0.1F
                }

                textView {
                    textResource = R.string.garis
                    textSize = 16f
                }.lparams{
                    topMargin = dip(5)
                    weight = 0.1F
                }

                textView {
                    id = Id.awayTeamScore
                    textSize = 16f
                    textColorResource = android.R.color.black
                }.lparams{
                    topMargin = dip(5)
                    weight = 0.1F
                }

                textView {
                    id = Id.awayTeamName
                    textSize = 16f
                    textColorResource = android.R.color.black
                    ellipsize = TextUtils.TruncateAt.END
                    singleLine = true
                    width = 0
                }.lparams{
                    topMargin = dip(5)
                    weight = 1.35F
                }
            }
        }

    }

    companion object Id {
        val eventDate = 1
        val homeTeamName = 2
        val homeTeamScore = 3
        val awayTeamName = 4
        val awayTeamScore = 5
    }
}