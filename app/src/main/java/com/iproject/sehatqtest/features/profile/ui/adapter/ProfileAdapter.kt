package com.iproject.sehatqtest.features.profile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iproject.sehatqtest.R
import com.iproject.sehatqtest.storage.history.History

class ProfileAdapter(private val historyList: List<History>) : RecyclerView.Adapter<ProfileViewHolder>() {
    interface OnItemClickListener {
        fun onDetailClick(position: Int)
    }

    private var itemListener: OnItemClickListener? = null

    fun setOnClickListener(listener: OnItemClickListener) {
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_search_list, parent, false)
        return ProfileViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bindItem(historyList[position])
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}