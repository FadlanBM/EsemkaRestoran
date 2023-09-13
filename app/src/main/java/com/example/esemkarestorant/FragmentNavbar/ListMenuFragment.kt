package com.example.esemkarestorant.FragmentNavbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.esemkarestorant.AdapterLayout.AdapterListMenu
import com.example.esemkarestorant.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListMenuFragment : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view=inflater.inflate(R.layout.fragment_list_menu, container, false)

        tabLayout=view.findViewById(R.id.tablayout_mainMenu)
        viewPager=view.findViewById(R.id.viewPagerMainMenu)

        val adapter=AdapterListMenu(childFragmentManager,tabLayout.tabCount)
        viewPager.adapter=adapter
          viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab?) {
            viewPager.currentItem=tab!!.position
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}

    })
        return view



    }
}