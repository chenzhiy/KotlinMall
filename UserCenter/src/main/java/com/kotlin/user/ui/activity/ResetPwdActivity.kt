package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.ResetPresenter
import com.kotlin.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.*

//注册界面
class ResetPwdActivity : BaseMvpActivity<ResetPresenter>(), ResetPwdView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()
    }

    //初始化视图
    private fun initView() {
        mResetPwdBtn.enable(mPwdEt) {isBtnEnable()}
        mResetPwdBtn.enable(mPwdConfirmEt) {isBtnEnable()}

        mResetPwdBtn.onClick(this)
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
            .userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.mResetPwdBtn -> {
                    if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                        toast("密码不一致")
                        return
                    }
                    mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.text.toString())
                }
            }
        }
    }

    private fun isBtnEnable():Boolean{
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }
}
