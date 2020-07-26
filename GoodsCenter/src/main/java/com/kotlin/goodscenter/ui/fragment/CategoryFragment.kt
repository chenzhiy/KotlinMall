package com.kotlin.goodscenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.kotlin.base.common.BaseApplication.Companion.context
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.goodscenter.R
import com.kotlin.goodscenter.data.protocol.Category
import com.kotlin.goodscenter.injection.module.CategoryModule
import com.kotlin.goodscenter.presenter.CategoryPresenter
import com.kotlin.goodscenter.presenter.view.CategoryView
import com.kotlin.goodscenter.ui.adapter.SecondCategoryAdapter
import com.kotlin.goodscenter.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.*
import com.kotlin.goodscenter.injection.component.DaggerCategoryComponent
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment:BaseMvpFragment<CategoryPresenter>(),CategoryView {
    private lateinit var topAdapter: TopCategoryAdapter
    private lateinit var secondAdapter: SecondCategoryAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_category,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        loadData()
    }

    private fun loadData(parentId:Int = 0) {
        mPresenter.getCategory(parentId)
    }

    private fun initView(){
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = context?.let { TopCategoryAdapter(it) }!!
        mTopCategoryRv.adapter = topAdapter
        topAdapter.setOnItemClickListener(object :BaseRecyclerViewAdapter.OnItemClickListener<Category>{
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList){
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()
                loadData(item.id)
            }
        })
        mSecondCategoryRv.layoutManager = GridLayoutManager(context,3)
        secondAdapter = SecondCategoryAdapter(context!!)

    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent)
            .categoryModule(CategoryModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    override fun onCategoryResult(result: MutableList<Category>?) {
        result?.let {
            if (result[0].parentId == 0){
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            }else{
                secondAdapter.setData(result)
                mMultiStateView.viewState = MultiStateView.ViewState.CONTENT
            }
        }
    }
}