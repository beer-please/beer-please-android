package com.ilya4.beerplease.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.view.adapter.holder.SearchHistoryViewHolder
import com.ilya4.beerplease.presentation.view.listener.OnSearchHistoryItemClickListener

class SearchHistoryAdapter(private val listener: OnSearchHistoryItemClickListener): RecyclerView.Adapter<SearchHistoryViewHolder>() {

    private var searchHistorySet: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_search_history, parent, false)

        return  SearchHistoryViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return if (searchHistorySet.size < 5) searchHistorySet.size else 5
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(searchHistorySet.elementAt(position))
    }

    fun setSearchHistoryItems(searchHistoryList: ArrayList<String>) {
        this.searchHistorySet = searchHistoryList
        this.searchHistorySet.reverse()
        notifyDataSetChanged()
    }

    fun clearSearchHistoryItems() {
        searchHistorySet.clear()
        notifyDataSetChanged()
    }
}