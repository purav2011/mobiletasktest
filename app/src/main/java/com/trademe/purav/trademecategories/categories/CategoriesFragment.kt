package com.trademe.purav.trademecategories.categories

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

class CategoriesFragment : DaggerFragment() {

    @Inject
    lateinit var factory: CategoriesViewModel.Factory

    private lateinit var messageView: TextView
    private lateinit var categoryList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders
                .of(this, factory)
                .get(CategoriesViewModel::class.java)

        viewModel.viewState.observe(this, Observer {
            when(it){
                is Loading -> {
                    messageView.visibility = View.VISIBLE
                    messageView.setText(R.string.loading)
                    categoryList.visibility = View.GONE
                }
                is Failure -> {
                    messageView.visibility = View.VISIBLE
                    messageView.text = it.error.localizedMessage
                    categoryList.visibility = View.GONE
                }
                is Success -> {
                    messageView.visibility = View.GONE
                    categoryList.visibility = View.VISIBLE
                    it.category.subcategories.let {
                        categoryList.adapter = CategoriesAdapter(it, {
                            viewModel.onCategorySelected(it)
                        })
                    }
                }
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater?.inflate(R.layout.fragment_categories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageView = view.findViewById<TextView>(R.id.message)
        categoryList = view.findViewById<RecyclerView>(R.id.category_list)
        categoryList.layoutManager = LinearLayoutManager(context)
    }
}