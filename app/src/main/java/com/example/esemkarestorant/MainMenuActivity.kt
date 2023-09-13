package com.example.esemkarestorant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.esemkarestorant.AdapterLayout.AdapterListMenu
import com.example.esemkarestorant.AdapterLayout.AdapterMainMenu
import com.example.esemkarestorant.databinding.ActivityMainMenuBinding
import com.google.android.material.tabs.TabLayout

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private var messageFormDetail : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageFormDetail = intent.getStringExtra("FROM_DETAIL")

        tabLayout=binding.tablayoutMainMenu
        viewPager=binding.viewPagerMainMenu

        val adapter=AdapterMainMenu(supportFragmentManager,tabLayout.tabCount)
        viewPager.adapter=adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem=tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        Log.e("TEST INTENT", messageFormDetail.toString())

        if (messageFormDetail == "FROM_DETAIL"){
            val tab = tabLayout.getTabAt(1)
            tabLayout.selectTab(tab)
        }
    }
}