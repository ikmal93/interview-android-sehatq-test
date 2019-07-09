package com.iproject.sehatqtest.features.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iproject.sehatqtest.R
import com.iproject.sehatqtest.storage.product.ProductPromo

class ProductAdapter(private val productPromoList: List<ProductPromo>) : RecyclerView.Adapter<ProductViewHolder>() {
    interface OnItemClickListener {
        fun onDetailClick(position: Int)
    }

    private var itemListener: OnItemClickListener? = null

    fun setOnClickListener(listener: OnItemClickListener) {
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_promo_list, parent, false)
        return ProductViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindItem(productPromoList[position])
    }

    override fun getItemCount(): Int {
        return productPromoList.size
    }
}