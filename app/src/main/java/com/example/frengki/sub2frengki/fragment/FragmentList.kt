package com.example.frengki.sub2frengki.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.frengki.sub2frengki.R
import com.example.frengki.sub2frengki.adapter.PertListAdapter
import com.example.frengki.sub2frengki.api.ApiRepository
import com.example.frengki.sub2frengki.model.Event
import com.example.frengki.sub2frengki.presenter.PertListPresenter
import com.example.frengki.sub2frengki.util.invisible
import com.example.frengki.sub2frengki.util.visible
import com.example.frengki.sub2frengki.view.PertListView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_fragment_list.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.onRefresh

private const val ARG_LIST_PREV_MATCH = "prevMatch"

class FragmentList : Fragment(), PertListView, AnkoLogger {
    private val leagueId: Int = 4328

    private var isPrevmatch: Boolean = true
    private var listener: OnFragmentInteractionListener? = null

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: PertListPresenter
    private lateinit var adapter: PertListAdapter

    private lateinit var eventsList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isPrevmatch = it.getBoolean(ARG_LIST_PREV_MATCH)
        }
        presenter = PertListPresenter(this, ApiRepository(), Gson())

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_fragment_list, container, false)

        progressBar = rootView.findViewById(R.id.progressBar)
        swipeRefresh = rootView.findViewById(R.id.swipeRefreshLayout)
        swipeRefresh.onRefresh {
            getEventsList()
        }
        adapter = PertListAdapter(events){
            val match = events.get(events.indexOf(it))
            listener?.onMatchListItemClick(match)
        }
        eventsList = rootView.findViewById(R.id.rvMatchList)


        eventsList.adapter = adapter
        return rootView
    }
    private fun getEventsList() {
        if(isPrevmatch) {
            presenter.getLast15EventsList(leagueId)
        }
        else {
            presenter.getNext15EventsList(leagueId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getEventsList()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    interface OnFragmentInteractionListener {
        fun onMatchListItemClick(match:Event)
    }

    companion object {
        @JvmStatic
        fun newInstance(isPrevmatch: Boolean) =
                FragmentList().apply {
                    arguments = Bundle().apply {
                        putBoolean(ARG_LIST_PREV_MATCH, isPrevmatch)
                    }
                }
    }
}
