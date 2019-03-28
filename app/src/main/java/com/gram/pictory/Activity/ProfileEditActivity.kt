package com.gram.pictory.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gram.pictory.Connect.Connecter.api
import com.gram.pictory.R
import kotlinx.android.synthetic.main.activity_profile_edit.*
import org.jetbrains.anko.toast

class ProfileEditActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        if (nameEditText.text.isNullOrBlank() or idSignUpEditText.text.isNullOrBlank()
            or pwSignUpEditText.text.isNullOrBlank() or birthEditText.text.isNullOrBlank()) {
            toast("정보를 모두 입력해주세요").show()
        } else {
            api.signEdit(hashMapOf(
                "id" to idSignUpEditText.text,
                "password" to pwSignUpEditText.text,
                "name" to nameEditText.text,
                "birth" to birthEditText.text
            ))
        }

        profileEditBtn.setOnClickListener {
            finish()
        }
    }
}