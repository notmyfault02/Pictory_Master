package com.gram.pictory.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gram.pictory.R
import com.gram.pictory.databinding.ActivityLoginBinding
import com.gram.pictory.ui.main.MainActivity
import com.gram.pictory.ui.signup.SignUpActivity
import com.gram.pictory.util.DataBindingActivity
import org.jetbrains.anko.startActivity

class LoginActivity : DataBindingActivity<ActivityLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_login

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        viewModel.goMainEvent.observe(this, Observer { startActivity<MainActivity>() })
        viewModel.goRegisterEvent.observe(this, Observer { startActivity<SignUpActivity>() })
    }

}