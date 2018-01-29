package com.trademe.purav.trademecategories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.trademe.purav.trademecategories.categories.CategoriesAdapter
import com.trademe.purav.trademecategories.listings.ListingsFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: MainViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        breadcrumb.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        viewModel.breadcrumb.observe(this, Observer {
            it?.let {
                breadcrumb.adapter = CategoriesAdapter(it, {
                    viewModel.onBreadcrumbItemClicked(it)
                })
                breadcrumb.scrollToPosition(it.lastIndex)

                it.last().let {
                    val categoryName = it.name
                    val categoryNumber = it.number
                    fab?.setOnClickListener {
                        startActivity(ListingsActivity
                                .newIntent(this, categoryName, categoryNumber))
                    }
                }
            }
        })

        if (findViewById<View>(R.id.listings) != null
                && supportFragmentManager.findFragmentById(R.id.listings) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.listings, ListingsFragment())
                    .commit()
        }
    }
}
