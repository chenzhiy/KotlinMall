package com.kotlin.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.mall.R
import com.kotlin.mall.common.HOME_BANNER_ONE
import com.kotlin.mall.common.HOME_BANNER_THREE
import com.kotlin.mall.common.HOME_BANNER_TWO
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.find

class HomeFragment:BaseFragment() {

    private lateinit var mHomeBanner:Banner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater?.inflate(R.layout.fragment_home,null)

        initBanner(rootView)
        return rootView
    }

    private fun initBanner(view:View){
        mHomeBanner = view.find(R.id.mHomeBanner)
        mHomeBanner.setImageLoader(BannerImageLoader()) //设置图片加载方式
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE)) //设置图片
        mHomeBanner.setBannerAnimation(Transformer.Accordion) //设置动画效果
        mHomeBanner.setDelayTime(2000) //设置切换时间
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT) //设置指示器位置
        mHomeBanner.start() //设置方法全部调用完毕是最后调用
    }
}