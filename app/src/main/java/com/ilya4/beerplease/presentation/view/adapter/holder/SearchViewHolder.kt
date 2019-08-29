package com.ilya4.beerplease.presentation.view.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ilya4.beerplease.R
import com.ilya4.beerplease.domain.entity.search.SearchItem
import com.ilya4.beerplease.presentation.view.listener.OnSearchItemClickListener
import com.ilya4.beerplease.utils.ViewUtils
import kotlinx.android.synthetic.main.item_search.view.*

class SearchViewHolder(itemView: View, private val listener: OnSearchItemClickListener) : RecyclerView.ViewHolder(itemView) {

    private lateinit var searchItem : SearchItem

    init {
        itemView.setOnClickListener { listener.onSearchItemClick(searchItem) }
    }

    fun bind(searchItem: SearchItem) {
        this.searchItem = searchItem

        itemView.itemBeerName.text = searchItem.name
        itemView.itemBeerGrade.text = searchItem.gradeBeer
        itemView.itemBeerCompany.text = searchItem.companyName
        itemView.itemRatingText.text = searchItem.rating.toString()

        val radius = ViewUtils.convertDpToPixel(4f, itemView.context)

        Glide.with(itemView)
            .load(R.drawable.pivo)
            .apply(RequestOptions.bitmapTransform(
                MultiTransformation(CenterCrop(),
                RoundedCorners(radius.toInt()))))
            .into(itemView.itemSearchImage)
    }
}