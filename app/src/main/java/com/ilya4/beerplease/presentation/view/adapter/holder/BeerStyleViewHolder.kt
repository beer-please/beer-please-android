package com.ilya4.beerplease.presentation.view.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ilya4.beerplease.presentation.view.listener.OnBeerStyleClickListener
import kotlinx.android.synthetic.main.item_style_beer.view.*

class BeerStyleViewHolder(itemView: View, private val beerStyleClickListener: OnBeerStyleClickListener): RecyclerView.ViewHolder(itemView) {

    private lateinit var beerStyleTitle: String

    fun bind(beerStyleTitle: String) {
        this.beerStyleTitle = beerStyleTitle
        itemView.styleBeerTV.text = beerStyleTitle

        itemView.setOnClickListener {
            beerStyleClickListener.onChooseBeerStyle(beerStyleTitle)
        }
    }

}