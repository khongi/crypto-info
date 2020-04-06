package com.thiosin.cryptoinfo.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thiosin.cryptoinfo.R
import com.thiosin.cryptoinfo.ui.list.CoinAdapter.CoinViewHolder
import com.thiosin.cryptoinfo.ui.list.ListPresenter.ListCoin
import kotlinx.android.synthetic.main.item_coin.view.*

class CoinAdapter : ListAdapter<ListCoin, CoinViewHolder>(CoinComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)
        holder.coin = coin

        holder.nameText.text = coin.name
        holder.priceText.text = coin.price
        holder.rankText.text = coin.rank
        holder.deltaText.text = coin.delta24h
        // TODO set delta color
        // TODO set image
    }

    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.nameText
        val priceText: TextView = itemView.priceText
        val rankText: TextView = itemView.rankText
        val deltaText: TextView = itemView.deltaText
        val coinImage: ImageView = itemView.coinImage

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
