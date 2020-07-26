package com.kotlin.mall.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.kotlin.base.ext.loadUrl
import com.kotlin.mall.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

//话题数据
class TopicAdapter(private val context: Context, private val list:List<String>) :PagerAdapter(){
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rootView = LayoutInflater.from(this.context).inflate(R.layout.layout_topic_item,null)
        rootView.mTopicIv.loadUrl(list[position])
        container.addView(rootView)
        return rootView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return (view == `object`)
    }

    override fun getCount(): Int {
        return this.list.size
    }

}