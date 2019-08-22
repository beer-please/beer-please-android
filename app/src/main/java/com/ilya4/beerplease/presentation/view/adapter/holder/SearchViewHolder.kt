package com.ilya4.beerplease.presentation.view.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ilya4.beerplease.domain.entity.search.SearchItem
import kotlinx.android.synthetic.main.item_search.view.*

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(searchItem: SearchItem) {
        itemView.itemBeerName.text = searchItem.name
        itemView.itemBeerGrade.text = searchItem.gradeBeer
        itemView.itemBeerCompany.text = searchItem.companyName
        itemView.itemRatingText.text = searchItem.rating.toString()
    }
}