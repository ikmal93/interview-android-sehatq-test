package com.iproject.sehatqtest.features.home.ui


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.iproject.sehatqtest.R
import com.iproject.sehatqtest.features.home.ui.adapter.CategoryAdapter
import com.iproject.sehatqtest.features.home.ui.adapter.ProductAdapter
import com.iproject.sehatqtest.features.product.ui.ProductDetailActivity
import com.iproject.sehatqtest.features.product.ui.ProductSearchActivity
import com.iproject.sehatqtest.rest.response.DataResponse
import com.iproject.sehatqtest.storage.DbHelper
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var presenter: HomeContract.Presenter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            DbHelper.getInstance(it)?.let { dbHelper ->
                presenter = HomePresenter(this, dbHelper)
            }
        }
        presenter.getData()


        home_search_card.setOnClickListener {
            context?.let {
                startActivity(Intent(it, ProductSearchActivity::class.java))
            }
        }
    }

    override fun showLoading() {
        home_progress_bar.visibility = View.VISIBLE
        home_progress_text.visibility = View.VISIBLE
        home_nested.visibility = View.INVISIBLE
    }

    override fun showData(dataResponse: List<DataResponse>) {
        home_progress_bar.visibility = View.GONE
        home_progress_text.visibility = View.GONE
        home_nested.visibility = View.VISIBLE

        home_category_recycler.setHasFixedSize(true)
        context?.let {
            home_category_recycler.layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
        }
        dataResponse[0].data.category.let {
            categoryAdapter = CategoryAdapter(it)
        }
        home_category_recycler.adapter = categoryAdapter

        categoryAdapter.setOnClickListener(object : CategoryAdapter.OnItemClickListener {
            override fun onDetailClick(position: Int) {
                Toast.makeText(requireContext(), "HAHA", Toast.LENGTH_SHORT).show()
            }

        })

        home_product_recycler.setHasFixedSize(true)
        context?.let {
            home_product_recycler.layoutManager = LinearLayoutManager(it)
        }
        dataResponse[0].data.productPromo.let {
            productAdapter = ProductAdapter(it)
        }
        home_product_recycler.adapter = productAdapter

        productAdapter.setOnClickListener(object : ProductAdapter.OnItemClickListener {
            override fun onDetailClick(position: Int) {
                context?.let {
                    startActivity(Intent(it, ProductDetailActivity::class.java)
                        .putExtra("id", dataResponse[0].data.productPromo[position].id))
                }
            }

        })
    }

    override fun showError() {

    }

    override fun onDetach() {
        super.onDetach()
        presenter.onViewDetached()
    }

    override fun onPause() {
        super.onPause()
        presenter.onViewDetached()
    }


}
