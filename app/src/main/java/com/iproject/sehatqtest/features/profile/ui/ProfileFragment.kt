package com.iproject.sehatqtest.features.profile.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.iproject.sehatqtest.R
import com.iproject.sehatqtest.features.profile.ui.adapter.ProfileAdapter
import com.iproject.sehatqtest.storage.DbHelper
import com.iproject.sehatqtest.storage.history.History
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment(), ProfileContract.View {

    private lateinit var presenter: ProfileContract.Presenter
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            DbHelper.getInstance(it)?.let { dbHelper ->
                presenter = ProfilePresenter(this, dbHelper)
            }
        }
        presenter.getHistory()

    }

    override fun showData(historyList: List<History>) {
        profile_recycler.setHasFixedSize(true)
        profile_recycler.layoutManager = LinearLayoutManager(requireContext())
        profileAdapter = ProfileAdapter(historyList)
        profile_recycler.adapter = profileAdapter

        profileAdapter.setOnClickListener(object : ProfileAdapter.OnItemClickListener {
            override fun onDetailClick(position: Int) {

            }

        })
    }


}
