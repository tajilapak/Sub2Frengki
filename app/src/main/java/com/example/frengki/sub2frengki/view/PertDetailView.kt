package com.example.frengki.sub2frengki.view

import com.example.frengki.sub2frengki.model.EventDetail
import com.example.frengki.sub2frengki.model.Team

interface PertDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailEvent(data: EventDetail)
    fun showDetailTeam(data: Team, isHomeTeam: Boolean)
}