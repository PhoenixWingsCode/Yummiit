package com.example.yummiit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.yummiit.adapter.MyAdapter
import com.example.yummiit.homeFragments.KidsFragment
import com.example.yummiit.homeFragments.MenFragment
import com.example.yummiit.homeFragments.WomenFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    private val tabNames = arrayOf("Men", "Women", "Kids")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.homeTabLayout)
        viewPager2 = view.findViewById(R.id.homeViewPager)

        val adapter = MyAdapter(childFragmentManager, lifecycle)
        viewPager2.adapter = adapter
        adapter.addFragment(MenFragment())
        adapter.addFragment(WomenFragment())
        adapter.addFragment(KidsFragment())

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
}