package com.xxunghee.android.geofencingexample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xxunghee.android.geofencingexample.data.GeoEntity

class MainAdapter(private val dataList: List<GeoEntity>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_db, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val inColor = "#E7FFAC"
        private val outColor = "#FFCBC1"

        private val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        private val tvStatue: TextView = itemView.findViewById(R.id.tv_status)

        fun bind(data: GeoEntity) {
            tvDate.text = data.date
            tvTime.text = data.time
            tvStatue.text = data.status

            when(data.status) {
                "출근" -> {
                    itemView.setBackgroundColor(Color.parseColor(inColor))
                }
                "퇴근" -> {
                    itemView.setBackgroundColor(Color.parseColor(outColor))
                }
            }
        }
    }
}