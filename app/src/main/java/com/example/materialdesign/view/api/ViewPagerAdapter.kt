package com.example.materialdesign.view.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val ADAPTER_SIZE = 10

class ViewPagerAdapter(private val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return ADAPTER_SIZE
    }

    override fun getItem(position: Int): Fragment {
        return BaseFragment.newInstance(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            BaseFragment.EARTH_FRAGMENT -> "Earth"
            BaseFragment.MARS_FRAGMENT -> "Mars"
            BaseFragment.SYSTEM_FRAGMENT -> "System"
            else -> "Earth"
        }
    }
}