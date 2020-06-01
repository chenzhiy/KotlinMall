package com.kotlin.user.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mPresenter = RegisterPresenter()
        mPresenter.mView = this

        mRegisterBtn.setOnClickListener {
            mPresenter.register(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())

        }
    }

    override fun onRegisterResult(result: Boolean) {
        toast("注册成功")
    }
}
