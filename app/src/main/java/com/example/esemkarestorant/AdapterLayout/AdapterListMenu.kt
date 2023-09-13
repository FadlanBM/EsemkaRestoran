package com.example.esemkarestorant.AdapterLayout

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.esemkarestorant.FrangmentTabLayout.AyamFragment
import com.example.esemkarestorant.FrangmentTabLayout.CemilanFragment
import com.example.esemkarestorant.FrangmentTabLayout.DagingSapiFragment
import com.example.esemkarestorant.FrangmentTabLayout.Happy_MealFragment
import com.example.esemkarestorant.FrangmentTabLayout.IkanFragment
import com.example.esemkarestorant.FrangmentTabLayout.MakananPenutupFragment
import com.example.esemkarestorant.FrangmentTabLayout.MinumanFragment
import com.example.esemkarestorant.FrangmentTabLayout.Paket_FamilyFragment
import com.example.esemkarestorant.FrangmentTabLayout.Sarapan_PagiFragment
import com.example.esemkarestorant.Util.SharePreft

internal class AdapterListMenu(val fr:FragmentManager,val totalTab:Int):FragmentPagerAdapter(fr) {

    override fun getItem(position: Int): Fragment {
        return when (position){
            0->
            {
                AyamFragment()
            }
            1->
            {
                CemilanFragment()
            }
            2->
            {
                DagingSapiFragment()
            }
            3->
            {
                Happy_MealFragment()
            }
            4->
            {
                IkanFragment()
            }
            5->
            {
                MakananPenutupFragment()
            }
            6->
            {
                MinumanFragment()
            }
            7->
            {
                Paket_FamilyFragment()
            }
            8->
            {
                Sarapan_PagiFragment()
            }
            else->getItem(position)
        }
    }

    override fun getCount(): Int {
        return 9
    }



}