package com.gram.pictory.ui.signup

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import com.gram.pictory.Connect.Connecter
import com.gram.pictory.R
import com.gram.pictory.Util.DataBindingActivity
import com.gram.pictory.databinding.ActivitySignupBinding
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity: DataBindingActivity<ActivitySignupBinding>(), SignUpNavigator {

    override val layoutId: Int
        get() = R.layout.activity_signup

    private val factory = SignUpViewModelFactory(this)
    private val viewModel: SignUpViewModel by lazy {
        ViewModelProviders.of(this, factory).get(SignUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        binding.setLifecycleOwner(this)

        profileImageView.onClick {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1)
        }

        signUpBtn.onClick {
            if (idSignUpEditText.text.isNullOrBlank() || pwSignUpEditText.text.isNullOrBlank()
                || nameEditText.text.isNullOrBlank() || birthEditText.text.isNullOrBlank()) {
                toast("정보를 모두 입력해주세요.").show()
            } else {
                Connecter.api.signUp(hashMapOf(
                    "id" to idSignUpEditText.text,
                    "password" to pwSignUpEditText.text,
                    "name" to nameEditText.text,
                    "birth" to birthEditText.text
                    )).enqueue(object: Callback<Unit>{

                    override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                        successSignUp()
                        toast("회원가입에 성공했습니다.").show()
                        finish()
                    }

                    override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                        failSignUp()
                        toast("회원가입에 실패했습니다.").show()
                        finish()
                    }
                })
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1) {
            if (resultCode === Activity.RESULT_OK) {
                try {
                    val inGallery = contentResolver.openInputStream(data!!.data)
                    val img = BitmapFactory.decodeStream(inGallery)
                    inGallery!!.close()
                    profileImageView.setImageBitmap(img)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    fun successSignUp(){
        // 토큰 저장
    }

    fun failSignUp(){
        // 토큰
    }

    override fun intentToLogin() {
        finish()
    }
}