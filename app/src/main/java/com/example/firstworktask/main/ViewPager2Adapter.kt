package com.example.firstworktask.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2Adapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    private var fragments: MutableList<Fragment> = mutableListOf()
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun setData(fragments: MutableList<Fragment>) {
        this.fragments = fragments
    }

}