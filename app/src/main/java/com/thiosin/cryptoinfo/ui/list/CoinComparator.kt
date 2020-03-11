package com.thiosin.cryptoinfo.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.thiosin.cryptoinfo.ui.list.ListPresenter.*

object CoinComparator : DiffUtil.ItemCallback<ListCoin>() {

    override fun areItemsTheSame(oldItem: ListCoin, newItem: ListCoin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ListCoin, newItem: ListCoin): Boolean {
        return oldItem == newItem
    }

}
