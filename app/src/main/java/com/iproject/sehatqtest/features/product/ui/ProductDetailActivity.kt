package com.iproject.sehatqtest.features.product.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.iproject.sehatqtest.R
import com.iproject.sehatqtest.storage.DbHelper
import com.iproject.sehatqtest.storage.history.History
import com.iproject.sehatqtest.storage.product.ProductPromo
import kotlinx.android.synthetic.main.product_detail_activity.*


class ProductDetailActivity : AppCompatActivity(),
    ProductDetailContract.View {

    private lateinit var presenter: ProductDetailContract.Presenter
    private var messageToShare: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_activity)
        val id = intent.getStringExtra("id")


        DbHelper.getInstance(this)?.let {
            presenter = ProductDetailPresenter(this, it)
        }
        presenter.getProduct(id)


    }

    override fun showLoading() {

    }

    override fun showData(productPromo: ProductPromo) {
        setToolbar(productPromo.title)
        messageToShare = productPromo.title
        Glide.with(this)
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
                    product_image.visibility = View.VISIBLE
                    return false
                }

            })
            .into(product_image)

        product_count_love.text = productPromo.loved.toString()
        product_description.text = productPromo.description
        product_price.text = productPromo.price

        val history = History()
        history.id = productPromo.id
        history.description = productPromo.description
        history.imageUrl = productPromo.imageUrl
        history.loved = productPromo.loved
        history.price = productPromo.price
        history.title = productPromo.title

        product_buy_btn.setOnClickListener {
            Toast.makeText(this, "Item added to history", Toast.LENGTH_SHORT).show()
            presenter.insert(history)
        }
    }

    override fun showError() {

    }

    private fun setToolbar(titleToolbar: String) {
        setSupportActionBar(product_toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = titleToolbar
        }

        product_toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.product_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.product_detail_share) {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, messageToShare)
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        return super.onOptionsItemSelected(item)

    }
}
