package com.kotlin.mall.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.kotlin.goodscenter.ui.fragment.CategoryFragment
import com.kotlin.mall.R
import com.kotlin.mall.ui.fragment.HomeFragment
import com.kotlin.mall.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    //集合栈
    private val mStack = Stack<Fragment>()

    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { HomeFragment() }
    private val mMsgFragment by lazy { HomeFragment() }
    private val mMeFragment by lazy { MeFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(20)

//        initView()
        initFragment()
        initBottomNav()
        changeFragment(0)
    }

    private fun initView(){
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContaier,HomeFragment())
        manager.commit()
    }

    private fun initFragment(){
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContaier,mHomeFragment)
        manager.add(R.id.mContaier,mCategoryFragment)
        manager.add(R.id.mContaier,mCartFragment)
        manager.add(R.id.mContaier,mMsgFragment)
        manager.add(R.id.mContaier,mMeFragment)
        manager.commit()

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)

    }

    private fun initBottomNav(){
        mBottomNavBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })
    }

    private fun changeFragment(position: Int){
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack){
            manager.hide(fragment)
        }
        manager.show(mStack[position])
        manager.commit()
    }
}
