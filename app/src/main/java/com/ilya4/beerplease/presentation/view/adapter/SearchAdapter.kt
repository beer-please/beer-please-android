package com.ilya4.beerplease.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilya4.beerplease.R
import com.ilya4.beerplease.domain.entity.search.SearchItem
import com.ilya4.beerplease.presentation.view.adapter.holder.SearchViewHolder

class SearchAdapter: RecyclerView.Adapter<SearchViewHolder>() {

    private var searchItems = ArrayList<SearchItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_search, parent, false)

        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchItems[position])
    }

    fun setSearchItems(searchItems: ArrayList<SearchItem>) {
        this.searchItems = searchItems
        notifyDataSetChanged()
    }

    fun clearSearchItems() {
        searchItems.clear()
        notifyDataSetChanged()
    }
}