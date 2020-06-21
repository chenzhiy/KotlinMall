package com.kotlin.user.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kotlin.base.common.AppManager
import com.kotlin.base.ext.onClick
import com.kotlin.base.injection.component.DaggerActivityComponent
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.widgets.VerifyButton
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import dagger.internal.DaggerCollections
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject
import kotlin.math.log


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn.onClick(View.OnClickListener {
            mPresenter.register(
                mMobileEt.text.toString(),
                mVerifyCodeEt.text.toString(),
                mPwdEt.text.toString()
            )
        })


//        mGetVerifyCodeBtn.setOnVerifyBtnClick(object :VerifyButton.OnVerifyBtnClick{
////             override fun onClick(){
////                 toast("获取验证码")
////            }
////        })
        mGetVerifyCodeBtn.setOnClickListener {
//            mGetVerifyCodeBtn.requestSendVerifyNumber()
//            toast("获取验证码成功")

            Observable.just("Hello World")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<String>() {
                    override fun onCompleted() {
                        //Completed
                    }

                    override fun onError(e: Throwable?) {
                        //TODO : Handle error here
                    }

                    override fun onNext(t: String?) {
                        Log.e("Output", t);
                    }
                })
        }


    }

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onBackPressed() {
        var time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }
}
