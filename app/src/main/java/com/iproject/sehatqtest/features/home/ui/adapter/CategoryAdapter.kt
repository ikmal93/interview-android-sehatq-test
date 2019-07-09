package com.iproject.sehatqtest.features.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iproject.sehatqtest.R
import com.iproject.sehatqtest.storage.category.Category

class CategoryAdapter(private val categoryList: List<Category>) : RecyclerView.Adapter<CategoryViewHolder>() {
    interface OnItemClickListener {
        fun onDetailClick(position: Int)
    }

    private var itemListener: OnItemClickListener? = null

    fun setOnClickListener(listener: OnItemClickListener) {
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_list, parent, false)
        return CategoryViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindItem(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}