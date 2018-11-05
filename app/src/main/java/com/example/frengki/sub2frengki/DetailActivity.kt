package com.example.frengki.sub2frengki

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import com.example.frengki.sub2frengki.api.ApiRepository
import com.example.frengki.sub2frengki.model.EventDetail
import com.example.frengki.sub2frengki.model.Team
import com.example.frengki.sub2frengki.presenter.PertDetailPresenter
import com.example.frengki.sub2frengki.util.formatDate
import com.example.frengki.sub2frengki.util.invisible
import com.example.frengki.sub2frengki.util.parse
import com.example.frengki.sub2frengki.util.visible
import com.example.frengki.sub2frengki.view.PertDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_activity.*

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class DetailActivity: AppCompatActivity(), PertDetailView, AppBarLayout.OnOffsetChangedListener, AnkoLogger {
    private val presenter: PertDetailPresenter = PertDetailPresenter(this, ApiRepository(), Gson())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        val matchId = intent.getStringExtra("MATCH_ID")
        val homeTeamId = intent.getStringExtra("HOME_TEAM_ID")
        val awayTeamId = intent.getStringExtra("AWAY_TEAM_ID")

        presenter.getEventDetail(matchId)

        presenter.getTeamDetail(homeTeamId)
        presenter.getTeamDetail(awayTeamId,false)

        bar.addOnOffsetChangedListener(this)
    }



    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        info("Offset: "+verticalOffset+" -> "+(verticalOffset+200)/-40f)
        animateToolbar(verticalOffset)


    }


    private fun animateToolbar(verticalOffset: Int) {
        when(verticalOffset) {
            // fade ins
            in -240..-200 -> {
                tglTvTollbar.visible()
                tglTvTollbar.animate().alpha((200 - verticalOffset)/40f)
                tglTvTollbar.animate().scaleX((verticalOffset+200)/-40f)
                tglTvTollbar.animate().scaleY((verticalOffset+200)/-40f)
                logoTuanRumah.visible()
                logoTuanRumah.animate().alpha((200 - verticalOffset)/40f)
                logoTuanRumah.animate().scaleX((verticalOffset+200)/-40f)
                logoTuanRumah.animate().scaleY((verticalOffset+200)/-40f)
                skorTv.visible()
                skorTv.animate().alpha((200 - verticalOffset)/40f)
                skorTv.animate().scaleX((verticalOffset+200)/-40f)
                skorTv.animate().scaleY((verticalOffset+200)/-40f)
                logoTamu.visible()
                logoTamu.animate().alpha((200 - verticalOffset)/40f)
                logoTamu.animate().scaleX((verticalOffset+200)/-40f)
                logoTamu.animate().scaleY((verticalOffset+200)/-40f)

            }

            //menghilang
            in -119..0 -> {
                tglTvTollbar.alpha = 0f
                tglTvTollbar.invisible()
                logoTuanRumah.alpha = 0f
                logoTuanRumah.invisible()
                skorTv.alpha = 0f
                skorTv.invisible()
                logoTamu.alpha = 0f
                logoTamu.invisible()
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showDetailEvent(match: EventDetail) {
        tglTv.text = match.eventDate?.formatDate()
        tglTvTollbar.text = match.eventDate?.formatDate()

        namaTuanRumah.text = match.homeTeam

        if (match.homeScore === null) {
            skorTv2.text = "vs"
            skorTv.text = " vs "
        }
        else {
            skorTv2.text = match.homeScore + " - " + match.awayScore
            skorTv.text = match.homeScore + " - " + match.awayScore
        }

        namaTimTamu.text = match.awayTeam

        formasiTv.text = match.homeFormation
        formasiTimTamu.text = match.awayFormation

        golTuanRumah.text = match.homeGoalDetails?.parse()
        golTamu.text = match.awayGoalDetails?.parse()

        shotTuanRumah.text = match.homeShots
        shotTamu.text = match.awayShots

        kiperTuanRumah.text = match.homeGoalKeeper
        kiperTamu.text = match.awayGoalKeeper

        defenderTuanRumah.text = match.homeDefense?.parse()
        defenderTamu.text = match.awayDefense?.parse()

        tuanRumahMid.text = match.homeMidfield?.parse()
        tamuMid.text = match.awayMidfield?.parse()

        strikerTuanRumah.text = match.homeForward?.parse()
        strikerTamu.text = match.awayForward?.parse()

        cadangan1.text = match.homeSubstitutes?.parse()
        cadangan2.text = match.awaySubstitutes?.parse()
    }

    override fun showDetailTeam(data: Team, isHomeTeam: Boolean) {
        Picasso.get().load(data.teamBadge).into(if(isHomeTeam) LogoTimTuanRumah else logoTimTamu)
        Picasso.get().load(data.teamBadge).into(if(isHomeTeam) logoTuanRumah else logoTamu)
    }
}