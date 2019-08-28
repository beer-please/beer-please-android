package com.ilya4.beerplease.presentation.view.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ilya4.beerplease.presentation.view.listener.OnSearchHistoryItemClickListener
import kotlinx.android.synthetic.main.item_search_history.view.*

class SearchHistoryViewHolder(itemView: View,
                              private val listener: OnSearchHistoryItemClickListener): RecyclerView.ViewHolder(itemView) {

    private lateinit var searchRequest: String

    init {
        itemView.setOnClickListener { listener.onSearchHistoryItemClick(searchRequest) }
    }

    fun bind(searchRequest: String) {
        this.searchRequest = searchRequest
        itemView.requestSearchTV.text = searchRequest
    }
}