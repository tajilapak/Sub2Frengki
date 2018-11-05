package com.example.frengki.sub2frengki.view

import com.example.frengki.sub2frengki.model.Event

interface PertListView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}