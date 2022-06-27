package com.example.firstworktask.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * This is the ViewPager2 Adapter which works anywhere.
 * Populate the fragments list using setData.
 * Can remove setData and make it hard-coded and specific for a fragment.
 * To do that, in createFragment, if(position==0) Fragment1.newInstance()... etc
 */
class ViewPager2Adapter(
    fragment: FragmentActivity
    ) : FragmentStateAdapter(fragment) {

    private var fragments: MutableList<Fragment> = mutableListOf()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(
        position: Int
    ): Fragment {
        return fragments[position]
    }

    fun setData(
        fragments: MutableList<Fragment>
    ) {
        this.fragments = fragments
    }

}