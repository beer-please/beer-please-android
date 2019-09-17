package com.ilya4.beerplease.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.view.adapter.holder.ReviewViewHolder

class ReviewAdapter(): RecyclerView.Adapter<ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_review_beer, parent, false)

        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind()
    }
}