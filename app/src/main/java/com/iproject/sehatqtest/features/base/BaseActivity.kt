package com.iproject.sehatqtest.features.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iproject.sehatqtest.R
import com.iproject.sehatqtest.features.home.ui.HomeFragment
import com.iproject.sehatqtest.features.news.NewsFragment
import com.iproject.sehatqtest.features.profile.ui.ProfileFragment

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val homeFragment = HomeFragment()
        initFragment(homeFragment)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

    private fun initFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.base_root_constraint, fragment).commit()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                val homeFragment = HomeFragment()
                initFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_news -> {
                val newsFragment = NewsFragment()
                initFragment(newsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                val profileFragment = ProfileFragment()
                initFragment(profileFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
