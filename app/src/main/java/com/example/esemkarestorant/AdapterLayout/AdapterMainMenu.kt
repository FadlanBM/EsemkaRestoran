package com.example.esemkarestorant.AdapterLayout

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.esemkarestorant.FragmentNavbar.CartFragment
import com.example.esemkarestorant.FragmentNavbar.ListMenuFragment
import com.example.esemkarestorant.FragmentNavbar.OrdersFragment

internal class AdapterMainMenu(fm:FragmentManager,val totalTab:Int):FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                ListMenuFragment()
            }
            1->{
                CartFragment()
            }
            2->{
                OrdersFragment()
            }
            else ->getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTab
    }

}