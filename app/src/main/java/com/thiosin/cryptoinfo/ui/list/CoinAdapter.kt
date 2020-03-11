package com.thiosin.cryptoinfo.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thiosin.cryptoinfo.R
import com.thiosin.cryptoinfo.ui.list.CoinAdapter.CoinViewHolder
import com.thiosin.cryptoinfo.ui.list.ListPresenter.*

class CoinAdapter : ListAdapter<ListCoin, CoinViewHolder>(CoinComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)
        holder.coin = coin

        // TODO set View data from coin
    }

    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO create View properties

        var coin: ListCoin? = null

        init {
            itemView.setOnClickListener {
                coin?.let { listener?.onCoinClicked(it) }
            }
        }
    }

    interface Listener {
        fun onCoinClicked(coin: ListCoin)
    }

}
