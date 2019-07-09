package com.iproject.sehatqtest.features.product.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.iproject.sehatqtest.R
import com.iproject.sehatqtest.features.product.ui.adapter.EmptySearchResult
import com.iproject.sehatqtest.features.product.ui.adapter.ProductSearchAdapter
import com.iproject.sehatqtest.storage.DbHelper
import com.iproject.sehatqtest.storage.product.ProductPromo
import kotlinx.android.synthetic.main.product_search_activity.*

class ProductSearchActivity : AppCompatActivity(), ProductSearchContract.View, EmptySearchResult {

    private lateinit var presenter: ProductSearchContract.Presenter
    private lateinit var productSearchAdapter: ProductSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_search_activity)
        setToolbar()
        DbHelper.getInstance(this)?.let {
            presenter = ProductSearchPresenter(this, it)
        }
        presenter.getAll()

        search_edit_text.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                productSearchAdapter.filter.filter(p0.toString())
            }

        })
    }

    override fun showLoading() {
        search_progress.visibility = View.VISIBLE
        search_progress_text.visibility = View.VISIBLE
        search_recycler.visibility = View.INVISIBLE
        search_not_found.visibility = View.INVISIBLE
        search_not_found_text.visibility = View.INVISIBLE
    }

    override fun showData(productPromoList: List<ProductPromo>) {
        search_progress.visibility = View.GONE
        search_progress_text.visibility = View.GONE
        search_recycler.visibility = View.VISIBLE
        search_not_found.visibility = View.INVISIBLE
        search_not_found_text.visibility = View.INVISIBLE


        search_recycler.setHasFixedSize(true)
        search_recycler.layoutManager = LinearLayoutManager(this)
        productSearchAdapter = ProductSearchAdapter(productPromoList, this)
        search_recycler.adapter = productSearchAdapter

        productSearchAdapter.setOnClickListener(object : ProductSearchAdapter.OnItemClickListener {
            override fun onDetailClick(position: Int) {

            }

        })
    }

    override fun showError() {

    }

    private fun setToolbar(){
        setSupportActionBar(search_toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = ""
        }
        search_toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun showEmptySearchResutl() {
        search_recycler.visibility = View.INVISIBLE
        search_not_found.visibility = View.VISIBLE
        search_not_found_text.visibility = View.VISIBLE
    }

    override fun hideEmptySearchResutl() {
        search_recycler.visibility = View.VISIBLE
        search_not_found.visibility = View.INVISIBLE
        search_not_found_text.visibility = View.INVISIBLE
    }
}
