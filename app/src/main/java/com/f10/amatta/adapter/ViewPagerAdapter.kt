package com.f10.amatta.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.f10.amatta.R

class ViewPagerAdapter(var items: ArrayList<String> = arrayListOf())
                : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = this.ViewHolder((parent))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.aespaMember.setText(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item, parent, false)) {
        val aespaMember = itemView.findViewById<TextView>(R.id.name)
    }

}