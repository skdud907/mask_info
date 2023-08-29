package com.example.maskinfo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maskinfo.R
import com.example.maskinfo.model.Store

class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
    var addressTextView: TextView = itemView.findViewById(R.id.addr_text_view)
    var distanceTextView: TextView = itemView.findViewById(R.id.distance_text_view)
    var remainStatTextView: TextView = itemView.findViewById(R.id.remain_text_view)
    var countTextView: TextView = itemView.findViewById(R.id.count_text_view)
}

class StoreAdapter: RecyclerView.Adapter<StoreViewHolder?>() {
    private var mItems: List<Store> = arrayListOf()

    fun updateItems(items: List<Store>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store: Store = mItems[position]
        holder.nameTextView.text = store.name
        holder.addressTextView.text = store.addr
        holder.distanceTextView.text = "1.0km"
        var remainStat = "충분"
        var count = ""
        var color = Color.GREEN

        when (store.remain_stat) {
            "plenty" -> {
                remainStat = "충분"
                count = "100개 이상"
                color = Color.GREEN
            }
            "some" -> {
                remainStat = "여유"
                count = "30개 이상"
                color = Color.YELLOW
            }
            "few" -> {
                remainStat = "매진 임박"
                count = "2개 이상"
                color = Color.RED
            }
            "empty" -> {
                remainStat = "재고없음"
                count = "1개 이하"
                color = Color.GRAY
            }
            else -> {
            }
        }
        holder.remainStatTextView.text = remainStat
        holder.countTextView.text = count
        holder.remainStatTextView.setTextColor(color)
        holder.countTextView.setTextColor(color)
    }
}