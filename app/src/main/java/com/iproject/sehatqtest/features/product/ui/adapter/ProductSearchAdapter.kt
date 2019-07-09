package com.iproject.sehatqtest.features.product.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.iproject.sehatqtest.R
import com.iproject.sehatqtest.storage.product.ProductPromo

class ProductSearchAdapter(private val productPromoList: List<ProductPromo>, searchResultView: EmptySearchResult) : RecyclerView.Adapter<ProductSearchViewHolder>(), Filterable,
        EmptySearchResult by searchResultView {

    private var listPromoFilter: List<ProductPromo> = productPromoList
    private var promoFilter: PromoFilter

    init {
        promoFilter = PromoFilter()
    }

    override fun getFilter(): Filter {
        return promoFilter
    }

    interface OnItemClickListener {
        fun onDetailClick(position: Int)
    }

    private var itemListener: OnItemClickListener? = null

    fun setOnClickListener(listener: OnItemClickListener) {
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_search_list, parent, false)
        return ProductSearchViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: ProductSearchViewHolder, position: Int) {
        holder.bindItem(productPromoList[position])
    }

    override fun getItemCount(): Int {
        return listPromoFilter.size
    }

    private inner class PromoFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
            val charString = charSequence.toString()
            if (charString.isEmpty()) {
                listPromoFilter = productPromoList
            } else {
                val filteredList = ArrayList<ProductPromo>()
                for (row in productPromoList) {

                    if (row.title.toLowerCase().contains(charString.toLowerCase())) {
                        filteredList.add(row)
                    }
                }

                listPromoFilter = filteredList
            }

            val filterResults = Filter.FilterResults()
            filterResults.values = listPromoFilter
            return filterResults
        }

        override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
            listPromoFilter = filterResults.values as ArrayList<ProductPromo>
            notifyDataSetChanged()

            if ((filterResults.values as List<ProductPromo>).size == 0) showEmptySearchResutl()
            else hideEmptySearchResutl()

        }
    }

}