package uz.muhsinov_dev.mohirdev_crypto_currency.adapter

import android.graphics.Color
import android.icu.util.BuddhistCalendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uz.muhsinov_dev.mohirdev_crypto_currency.R
import uz.muhsinov_dev.mohirdev_crypto_currency.data.models.Coin
import uz.muhsinov_dev.mohirdev_crypto_currency.databinding.ItemRvBinding
import uz.muhsinov_dev.mohirdev_crypto_currency.utils.Constants

class RvAdapter(var list: List<Coin>) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {

        fun onBind(coin: Coin) {
            itemRv.coinName.text = coin.rank.toString() + " "+ coin.name
            itemRv.coinStatus.text = if (true) "Active" else "Not Active"
            itemRv.coinStatus.setTextColor(if (true) Color.GREEN else Color.RED)
            itemRv.root.setOnClickListener {
                itemRv.root.findNavController().navigate(R.id.action_fragmentCoinList_to_coinInfoFragment,
                    bundleOf(Constants.coinId to coin.id)
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}