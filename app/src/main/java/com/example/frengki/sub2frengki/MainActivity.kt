package com.example.frengki.sub2frengki

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import com.example.frengki.sub2frengki.fragment.FragmentList
import android.support.design.widget.BottomNavigationView
import com.example.frengki.sub2frengki.model.Event
import com.example.frengki.sub2frengki.util.addFragment
import com.example.frengki.sub2frengki.util.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), FragmentList.OnFragmentInteractionListener, AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



       navMatch.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId){
                R.id.navigate_prev_match-> {
                    replaceFragment(FragmentList.newInstance(true), framelayout.id)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigate_next_match -> {
                    replaceFragment(FragmentList.newInstance(isPrevmatch = false), framelayout.id)
                    return@OnNavigationItemSelectedListener true
                }
            }
            return@OnNavigationItemSelectedListener false
        })

        addFragment(FragmentList(), framelayout.id)
    }

    override fun onMatchListItemClick(match: Event) {
        startActivity<DetailActivity>(
                "MATCH_ID" to match.eventId,
                "HOME_TEAM_ID" to match.homeTeamId,
                "AWAY_TEAM_ID" to match.awayTeamId
        )

    }

}
