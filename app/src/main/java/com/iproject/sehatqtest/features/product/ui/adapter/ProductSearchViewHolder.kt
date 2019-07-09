package com.iproject.sehatqtest.features.product.ui.adapter

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.iproject.sehatqtest.storage.product.ProductPromo
import kotlinx.android.synthetic.main.product_search_list.view.*

class ProductSearchViewHolder(itemView: View, itemListener: ProductSearchAdapter.OnItemClickListener?): RecyclerView.ViewHolder(itemView){
    private val list: ConstraintLayout = itemView.search_list_constraint
    private val image: ImageView = itemView.search_image
    private val name: TextView = itemView.search_name
    private val description: TextView = itemView.search_description

    init {
        list.setOnClickListener {
            if (itemListener != null) {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemListener.onDetailClick(adapterPosition)
                }
            }
        }
    }

    fun bindItem(productPromo: ProductPromo){
        name.text = productPromo.title
        description.text = productPromo.price
        Glide.with(itemView.context)
                .load(productPromo.imageUrl)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .apply(RequestOptions.circleCropTransform())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                    ): Boolean {
                        image.visibility = View.VISIBLE
                        return false
                    }

                })
                .into(image)
    }
}