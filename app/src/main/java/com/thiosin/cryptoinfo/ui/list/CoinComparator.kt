package com.thiosin.cryptoinfo.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.thiosin.cryptoinfo.ui.list.ListPresenter.ListCoin

object CoinComparator : DiffUtil.ItemCallback<ListCoin>() {

    override fun areItemsTheSame(oldItem: ListCoin, newItem: ListCoin): Boolean {
        return oldItem.symbol == newItem.symbol
    }

    override fun areContentsTheSame(oldItem: ListCoin, newItem: ListCoin): Boolean {
        return oldItem == newItem
    }

}
