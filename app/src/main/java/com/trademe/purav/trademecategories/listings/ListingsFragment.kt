package com.trademe.purav.trademecategories.listings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.trademe.purav.trademecategories.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListingsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ListingsViewModel.Factory

    private lateinit var messageView: TextView
    private lateinit var listingsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders
                .of(this, factory)
                .get(ListingsViewModel::class.java)

        viewModel.viewState.observe(this, Observer {
            when (it) {
                is Loading -> {
                    messageView.setText(R.string.loading)
                    messageView.visibility = View.VISIBLE
                    listingsList.visibility = View.GONE
                }
                is Failure -> {
                    messageView.visibility = View.VISIBLE
                    messageView.text = it.error.localizedMessage
                    listingsList.visibility = View.GONE
                }
                is Success -> {
                    it.searchResult.list.let {
                        messageView.visibility = View.GONE
                        listingsList.visibility = View.VISIBLE
                        listingsList.adapter = ListingsAdapter(it)
                    }
                }
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater?.inflate(R.layout.fragment_listings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageView = view.findViewById<TextView>(R.id.message)
        listingsList = view.findViewById<RecyclerView>(R.id.listings_list)
        listingsList.layoutManager = LinearLayoutManager(context)
    }
}