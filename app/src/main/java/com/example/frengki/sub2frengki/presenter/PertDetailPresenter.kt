package com.example.frengki.sub2frengki.presenter

import com.example.frengki.sub2frengki.api.ApiRepository
import com.example.frengki.sub2frengki.api.TheSportDbApi
import com.example.frengki.sub2frengki.model.EventDetailResponse
import com.example.frengki.sub2frengki.model.TeamResponse
import com.example.frengki.sub2frengki.view.PertDetailView
import com.google.gson.Gson
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PertDetailPresenter(private val view: PertDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson): AnkoLogger {
    fun getEventDetail(eventId: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getEventDetail(eventId)),
                    EventDetailResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailEvent(data.events.get(0))
            }
        }
    }

    fun getTeamDetail(teamId: String, isHomeTeam: Boolean=true) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getTeamDetail(teamId)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailTeam(data.teams.get(0), isHomeTeam)
            }
        }
    }
}