package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.ForgetPwdPresenter
import com.kotlin.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

//忘记密码界面
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    //初始化视图
    private fun initView() {
        mNextBtn.enable(mMobileEt) {isBtnEnable()}
        mNextBtn.enable(mVerifyCodeEt) {isBtnEnable()}

        mGetVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)
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
                R.id.mGetVerifyCodeBtn -> {
                    mGetVerifyCodeBtn.requestSendVerifyNumber()
                    toast("发送验证码成功")
                }
                R.id.mNextBtn -> {
                    mPresenter.forgetPwd(mMobileEt.text.toString(), mVerifyCodeEt.text.toString()
                    )
                }
            }
        }
    }

    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() &&
                mGetVerifyCodeBtn.text.isNullOrEmpty().not()
    }

    //忘记密码回调
    override fun onForgetPwdResult(result: String) {
        toast("提交成功")
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
    }
}
