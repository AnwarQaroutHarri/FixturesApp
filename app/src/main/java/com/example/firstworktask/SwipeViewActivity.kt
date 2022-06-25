package com.example.firstworktask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.firstworktask.databinding.ActivitySwipeViewBinding
import com.example.firstworktask.main.FirstDateFragment
import com.example.firstworktask.main.SecondDateFragment
import com.example.firstworktask.main.ThirdDateFragment
import com.example.firstworktask.main.ViewPager2Adapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SwipeViewActivity : AppCompatActivity(), TabLayoutMediator.TabConfigurationStrategy {
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    var titles: MutableList<String> = mutableListOf("Yesterday","Today","Tomorrow")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_view)

        val binding: ActivitySwipeViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_swipe_view)
       viewPager = binding.viewPager2
        tabLayout = binding.tabLayout
        setViewPagerAdapter()
        TabLayoutMediator(tabLayout,viewPager,this).attach()

       // println(tabLayout.selectedTabPosition.toString())

    }

    fun setViewPagerAdapter() {
        val viewPager2Adapter = ViewPager2Adapter(this);
        var fragmentList : MutableList<Fragment> = mutableListOf(
            FirstDateFragment(),
            SecondDateFragment(),
            ThirdDateFragment()
        )
        viewPager2Adapter.setData(fragmentList)
        viewPager.adapter = viewPager2Adapter

    }

    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        tab.text = titles[position]
    }
}