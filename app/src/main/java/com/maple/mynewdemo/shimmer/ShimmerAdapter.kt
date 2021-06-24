package com.maple.mynewdemo.shimmer

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maple.mynewdemo.R
import org.jetbrains.anko.layoutInflater

class ShimmerAdapter(val context: Context): RecyclerView.Adapter<ShimmerAdapter.ViewHolder>() {

    private val list: MutableList<String> = mutableListOf()

    fun clearList () {
        if(!list.isNullOrEmpty()) {
            this.list.clear()
            this.notifyDataSetChanged()
        }
    }
    fun updateList (l: MutableList<String>) {
        if(!l.isNullOrEmpty()) {
            this.list.addAll(l)
            this.notifyDataSetChanged()
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = (parent.context?: context).layoutInflater.inflate(R.layout.item_shimmer, parent, false)
        val holder: ViewHolder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list.get(position))
    }

    override fun getItemCount(): Int {
        return if(list.isNullOrEmpty()) 0 else list.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvInfo: TextView = itemView.findViewById(R.id.tv_info)
        fun setData(data: String?) {
            data?.let {
                tvInfo.text = it
            }
        }

    }

}