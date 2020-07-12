package com.kotlin.user.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.bumptech.glide.Glide
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.common.BaseConstant
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.UserInfoPresenter
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.presenter.view.UserInfoView
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File

//用户信息
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView ,TakePhoto.TakeResultListener{

    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile:File
    private val mUploadManager: UploadManager by lazy { UploadManager() }

    private var mLocalFile:String? = null

    private var mRemoteFile:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this,this)
        initView()

        mTakePhoto.onCreate(savedInstanceState)
    }

    //初始化视图
    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }
    }

    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this, AlertView.Style.ActionSheet,
            OnItemClickListener { o, position ->
                //压缩处理
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false)
                when (position) {
                    0 -> {
                        createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                    }
                    1 -> mTakePhoto.onPickFromGallery()
                }
            }).show()
    }

    //生成文件路径
    private fun createTempFile(){
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()){
                this.mTempFile = File(Environment.getExternalStorageDirectory(),tempFileName)
                return
            }
        else{
            this.mTempFile = File(filesDir,tempFileName)
        }
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
            .userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    //获取成功
    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result?.image?.originalPath)
        Log.d("TakePhoto", result?.image?.compressPath)
        mLocalFile = result?.image?.compressPath //将压缩后的文件赋值
        mPresenter.getUploadToken()
    }

    //取消
    override fun takeCancel() {
        TODO("Not yet implemented")
    }

    //获取失败
    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto", msg)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode,resultCode,data)
    }

    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFile,null,result,object:UpCompletionHandler{
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                if (response != null) {
                    mRemoteFile = BaseConstant.IMAGE_SERVER_ADDRESS + response.get("hash")
                    mRemoteFile = "https://a06frontweb.cathayfund.com/cdn/A06FP/img/roma.c67417ca.png"
                }

                //加载图片
                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFile!!,mUserIconView)
            }
        },null)
    }
}
