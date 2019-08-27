package com.ilya4.beerplease.presentation.view.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search_history.view.*

class SearchHistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(searchRequest: String) {
        itemView.requestSearchTV.text = searchRequest
    }
}