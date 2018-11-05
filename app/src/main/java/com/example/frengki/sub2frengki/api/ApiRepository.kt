package com.example.frengki.sub2frengki.api

import java.net.URL

class ApiRepository{
    fun doRequest (url: String): String  {
        return URL(url).readText()
    }
}