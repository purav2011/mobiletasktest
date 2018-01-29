package com.trademe.purav.trademecategories.listings

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.trademe.purav.trademecategories.R
import com.trademe.purav.trademecategories.data.Listing

/**
 * Created by purav on 30/01/2018.
 */
class ListingsAdapter(
        private val listings: List<Listing>
) : RecyclerView.Adapter<ListingsAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.thumbnailView.context)
                .load(listings[position].pictureHref)
                .into(holder.thumbnailView);
        holder.titleView.text = listings[position].title
        holder.idView.text = "${listings[position].id}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listingView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_listing, parent, false) as View
        return ViewHolder(listingView)
    }

    override fun getItemCount() = listings.size

    class ViewHolder(listingView: View) : RecyclerView.ViewHolder(listingView) {
        val thumbnailView: ImageView = listingView.findViewById<ImageView>(R.id.thumbnail)
        val titleView: TextView = listingView.findViewById<TextView>(R.id.title)
        val idView: TextView = listingView.findViewById<TextView>(R.id.id)
    }
}