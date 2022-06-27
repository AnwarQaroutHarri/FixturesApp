package com.example.firstworktask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.firstworktask.databinding.ActivitySwipeViewBinding
import com.example.firstworktask.main.CalendarFragment
import com.example.firstworktask.main.FixturesFragment
import com.example.firstworktask.main.ViewPager2Adapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class SwipeViewActivity : AppCompatActivity(), TabLayoutMediator.TabConfigurationStrategy {

    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    var titles: MutableList<String> = mutableListOf("Yesterday","Today","Tomorrow","Date")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_view)

        val binding: ActivitySwipeViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_swipe_view)
       viewPager = binding.viewPager2
        tabLayout = binding.tabLayout

        setViewPagerAdapter()
        TabLayoutMediator(tabLayout,viewPager,this).attach() //attach tablayout to viewpager

    }

    /*
    Instantiate a ViewPagerAdapter, and populate it with fragments, then set it to ViewPager.
     */
    fun setViewPagerAdapter() {
        val viewPager2Adapter = ViewPager2Adapter(this);
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        var fragmentList: MutableList<Fragment> = mutableListOf(
            FixturesFragment.newInstance(current.minusDays(1).format(formatter)),
            FixturesFragment.newInstance(current.format(formatter)),
            FixturesFragment.newInstance(current.plusDays(1).format(formatter)),
            CalendarFragment()
        )

        viewPager2Adapter.setData(fragmentList)
        viewPager.adapter = viewPager2Adapter
    }

    /*
        This function is needed to bind tabs to their titles (in this case, a string list).
     */
    override fun onConfigureTab(
        tab: TabLayout.Tab,
        position: Int
    ) {

        tab.text = titles[position]

    }
}