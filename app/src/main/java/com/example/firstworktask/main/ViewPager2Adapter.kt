package com.example.firstworktask.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ViewPager2Adapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    private var fragments: MutableList<Fragment> = mutableListOf()
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {

        return fragments[position]

        /*
        if(position == 0) {
            return FixturesFragment.newInstance(current.minusDays(1).format(formatter))
        }
        if(position == 1) {
            return FixturesFragment.newInstance(current.format(formatter))
        }
        if(position == 2){
            return FixturesFragment.newInstance(current.plusDays(1).format(formatter))
        }
        return CalendarFragment()

         */

    }


    fun setData(fragments: MutableList<Fragment>) {
        this.fragments = fragments
    }

}