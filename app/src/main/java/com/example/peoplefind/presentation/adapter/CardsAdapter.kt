package com.example.peoplefind.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.peoplefind.R
import com.example.peoplefind.domain.model.response.CardItem

class CardsAdapter : ListAdapter<CardItem, CardsAdapter.Holder>(Comparator()) {
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class Comparator : DiffUtil.ItemCallback<CardItem>() {
        override fun areItemsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("Not yet implemented")
    }
}