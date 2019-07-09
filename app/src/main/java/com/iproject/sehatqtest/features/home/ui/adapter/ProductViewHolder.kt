package com.iproject.sehatqtest.features.home.ui.adapter

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
import kotlinx.android.synthetic.main.product_promo_list.view.*

class ProductViewHolder(itemView: View, itemListener: ProductAdapter.OnItemClickListener?) :
    RecyclerView.ViewHolder(itemView) {
    private val list: ConstraintLayout = itemView.product_list_constraint
    private val image: ImageView = itemView.product_image_list
    private val description: TextView = itemView.product_content_list
    private val name: TextView = itemView.product_name

    init {
        list.setOnClickListener {
            if (itemListener != null) {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemListener.onDetailClick(adapterPosition)
                }
            }
        }
    }

    fun bindItem(productPromo: ProductPromo) {
        name.text = productPromo.title
        description.text = productPromo.description
        Glide.with(itemView.context)
            .load(productPromo.imageUrl)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
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