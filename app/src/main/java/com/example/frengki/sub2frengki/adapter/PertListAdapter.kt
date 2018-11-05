package com.example.frengki.sub2frengki.adapter

import android.app.usage.UsageEvents
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.frengki.sub2frengki.layout.MatchListItemUi
import com.example.frengki.sub2frengki.model.Event
import com.example.frengki.sub2frengki.util.formatDate
import com.example.frengki.sub2frengki.util.bold
import com.example.frengki.sub2frengki.util.normal
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

class PertListAdapter(private val events: List<Event>, private val listener : (Event) -> Unit):RecyclerView.Adapter<PertListAdapter.PertViewHolder>(),AnkoLogger{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):PertViewHolder {
        return PertViewHolder(MatchListItemUi().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = events.size
        override fun onBindViewHolder(holder: PertListAdapter.PertViewHolder, position: Int) {
            holder.bindItem(events[position],listener)
        }
        class PertViewHolder(view: View): RecyclerView.ViewHolder(view), AnkoLogger{

            private val eventDate: TextView = view.find(MatchListItemUi.eventDate)
            private val homeTeamName: TextView = view.find(MatchListItemUi.homeTeamName)
            private val homeTeamScore: TextView = view.find(MatchListItemUi.homeTeamScore)
            private val awayTeamScore: TextView = view.find(MatchListItemUi.awayTeamScore)
            private val awayTeamName: TextView = view.find(MatchListItemUi.awayTeamName)

            fun bindItem(events: Event, listener: (Event) -> Unit) {

                eventDate.text = events.eventDate?.formatDate()

                homeTeamName.text = events.homeTeam
                homeTeamScore.text = events.homeScore
                awayTeamScore.text = events.awayScore
                awayTeamName.text = events.awayTeam

                val homeScore = events.homeScore?.toInt()?:0
                val awayScore = events.awayScore?.toInt()?:0

                if(homeScore-awayScore>0) {
                    homeTeamName.bold()
                    awayTeamName.normal()
                }
                else {
                    if(homeScore-awayScore<0) {
                        homeTeamName.normal()
                        awayTeamName.bold()
                    }
                    else {
                        homeTeamName.normal()
                        awayTeamName.normal()
                    }
                }

                itemView.setOnClickListener {
                    listener(events)
                }

            }
        }
    }

