package com.gram.pictory.ui.login

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gram.pictory.R
import com.gram.pictory.util.DataBindingActivity
import com.gram.pictory.databinding.ActivityLoginBinding
import com.gram.pictory.ui.main.MainActivity
import com.gram.pictory.ui.signup.SignUpActivity
import org.jetbrains.anko.startActivity

class LoginActivity : DataBindingActivity<ActivityLoginBinding>(), LoginConstract {

    override val layoutId: Int
        get() = R.layout.activity_login

    private val factory = LoginViewModelFactory(this)
    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
    }

    override fun intentToSignUp() {
        startActivity<SignUpActivity>()
    }

    override fun intentToMain() {
        startActivity<MainActivity>()
        finish()
    }

}