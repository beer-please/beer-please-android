package com.ilya4.beerplease.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilya4.beerplease.R
import com.ilya4.beerplease.domain.entity.search.SearchItem
import com.ilya4.beerplease.presentation.view.adapter.holder.SearchViewHolder
import com.ilya4.beerplease.presentation.view.listener.OnSearchItemClickListener

class SearchAdapter(private val listener: OnSearchItemClickListener): RecyclerView.Adapter<SearchViewHolder>() {

    private var searchItems = ArrayList<SearchItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_search, parent, false)

        return SearchViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchItems[position])
    }

    fun setSearchItems(searchItems: List<SearchItem>) {
        this.searchItems = searchItems as ArrayList<SearchItem>
        notifyDataSetChanged()
    }

    fun clearSearchItems() {
        searchItems.clear()
        notifyDataSetChanged()
    }
}