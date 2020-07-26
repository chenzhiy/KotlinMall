package com.kotlin.goodscenter.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.goodscenter.R
import com.kotlin.goodscenter.data.protocol.Category
import kotlinx.android.synthetic.main.fragment_category.view.*

class TopCategoryAdapter(context:Context): BaseRecyclerViewAdapter<Category, TopCategoryAdapter.ViewHolder>(context) {


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_top_category_item,parent,false)

        return ViewHolder(view)
    }
}