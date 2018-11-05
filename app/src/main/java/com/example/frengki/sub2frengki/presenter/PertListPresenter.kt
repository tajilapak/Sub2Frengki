package com.example.frengki.sub2frengki.presenter

import com.example.frengki.sub2frengki.api.ApiRepository
import com.example.frengki.sub2frengki.api.TheSportDbApi
import com.example.frengki.sub2frengki.model.EventResponse
import com.example.frengki.sub2frengki.view.PertListView
import com.google.gson.Gson
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PertListPresenter(private val view: PertListView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson): AnkoLogger {
    fun getLast15EventsList(leagueId: Int?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getLast15Events(leagueId)),
                    EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }

    fun getNext15EventsList(leagueId: Int?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getNext15Events(leagueId)),
                    EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }
}