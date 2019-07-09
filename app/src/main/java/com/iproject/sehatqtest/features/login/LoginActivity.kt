package com.iproject.sehatqtest.features.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iproject.sehatqtest.features.base.BaseActivity
import com.iproject.sehatqtest.R
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        login_sign_in_btn.setOnClickListener {
            startActivity(Intent(this, BaseActivity::class.java))
        }

    }
}
