package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

//注册界面
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    //初始化视图
    private fun initView() {
//        mRegisterBtn.enable(mMobileEt) {isBtnEnable()}
        mRegisterBtn.enable(mMobileEt, {isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt) {isBtnEnable()}
        mRegisterBtn.enable(mPwdEt) {isBtnEnable()}
        mRegisterBtn.enable(mPwdConfirmEt) {isBtnEnable()}

        mGetVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)
    }

//    override fun onBackPressed() {
//        var time = System.currentTimeMillis()
//        if (time - pressTime > 2000) {
//            toast("再按一次退出程序")
//            pressTime = time
//        } else {
//            AppManager.instance.exitApp(this)
//        }
//    }

    //注册回调
    override fun onRegisterResult(result: String) {
        toast(result)
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
                R.id.mRegisterBtn -> {
                    mPresenter.register(
                        mMobileEt.text.toString(),
                        mVerifyCodeEt.text.toString(),
                        mPwdEt.text.toString()
                    )
                }
            }
        }
    }

    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() &&
                mGetVerifyCodeBtn.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}
