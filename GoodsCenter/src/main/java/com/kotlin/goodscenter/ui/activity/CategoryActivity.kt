package com.kotlin.goodscenter.ui.activity

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
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.goodscenter.data.protocol.Category
import com.kotlin.goodscenter.presenter.CategoryPresenter
import com.kotlin.goodscenter.presenter.view.CategoryView
import com.kotlin.provider.common.ProviderConstant

import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File

//用户信息
class CategoryActivity : BaseMvpActivity<CategoryPresenter>(), CategoryView{
    override fun injectComponent() {
        TODO("Not yet implemented")
    }

    override fun onCategoryResult(result: MutableList<Category>?) {
        TODO("Not yet implemented")
    }


}
