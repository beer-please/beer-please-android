package com.ilya4.beerplease.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.view.adapter.holder.BeerStyleViewHolder

class BeerStyleAdapter: RecyclerView.Adapter<BeerStyleViewHolder>() {

    private var stylesBeerList: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerStyleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_style_beer, parent, false)

        return BeerStyleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stylesBeerList.size
    }

    override fun onBindViewHolder(holder: BeerStyleViewHolder, position: Int) {
        holder.bind(stylesBeerList.elementAt(position))
    }

    fun setStyleBeerList(styleBeerList: List<String>) {
        this.stylesBeerList = styleBeerList as ArrayList<String>
        notifyDataSetChanged()
    }
}