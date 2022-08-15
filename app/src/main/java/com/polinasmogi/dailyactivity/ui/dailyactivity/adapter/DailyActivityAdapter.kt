package com.polinasmogi.dailyactivity.ui.dailyactivity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polinasmogi.dailyactivity.R
import com.polinasmogi.dailyactivity.databinding.ItemDailyProgressBinding
import com.polinasmogi.dailyactivity.databinding.ItemTimelineBinding
import com.polinasmogi.dailyactivity.models.DailyActivityWrapper

class DailyActivityAdapter : RecyclerView.Adapter<DailyActivityViewHolder>() {

    private val items = arrayListOf<DailyActivityWrapper>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyActivityViewHolder {
        val binding = ItemDailyProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyActivityViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun build(list: List<DailyActivityWrapper>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

}