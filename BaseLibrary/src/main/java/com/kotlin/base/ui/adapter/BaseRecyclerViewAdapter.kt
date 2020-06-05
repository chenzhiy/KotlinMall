package com.kotlin.base.ui.adapter

import android.content.Context
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T,VH:RecyclerView.ViewHolder>
    (var mContext: Context): RecyclerView.Adapter<VH>() {
    //ItemClick事件
    var mItemClickListener: OnItemClickListener<T>? = null

    //数据集合
    var dataList: MutableList<T> = mutableListOf()


    //设置数据  Presenter处理过为null的情况，所以不会为null
    fun setData(sources: MutableList<T>){
        dataList = sources
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            if (mItemClickListener != null){
                mItemClickListener!!.onItemClick(dataList[position],position)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    //ItemClick事件声明
    interface OnItemClickListener<in T>{
        fun onItemClick(item:T,position: Int)
    }

    fun setOnItemClickListener(listener:OnItemClickListener<T>){
        this.mItemClickListener = listener
    }


}