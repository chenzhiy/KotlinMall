package com.kotlin.user.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import dagger.internal.DaggerCollections
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject
import kotlin.math.log


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mPresenter = RegisterPresenter()
        initInjection()

        mRegisterBtn.setOnClickListener {
            mPresenter.register(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
//            toast("注册成功")


        }
    }

    override fun onRegisterResult(result: Boolean) {
        Log.d("msg","这是子类onRegisterResult")
        toast("注册成功")
    }

    private fun initInjection(){

        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)

        mPresenter.mView = this
    }
}
