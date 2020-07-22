package com.kotlin.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.mall.R
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.HomeDiscountAdapter
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.find

class HomeFragment:BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater?.inflate(R.layout.fragment_home,null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBanner()
        initNews()
        initDiscount()
    }

    private fun initBanner(){
        mHomeBanner.setImageLoader(BannerImageLoader()) //设置图片加载方式
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE)) //设置图片
        mHomeBanner.setBannerAnimation(Transformer.Accordion) //设置动画效果
        mHomeBanner.setDelayTime(2000) //设置切换时间
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT) //设置指示器位置
        mHomeBanner.start() //设置方法全部调用完毕是最后调用
    }

    private fun initNews(){
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒达到战场","新用户立领1000元优惠券"))
    }

    private fun initDiscount(){
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager

        val discountAdapter = activity?.let { HomeDiscountAdapter(it) }
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter?.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE,
            HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
    }
}