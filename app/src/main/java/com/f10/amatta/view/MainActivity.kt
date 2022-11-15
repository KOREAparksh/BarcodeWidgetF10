package com.f10.amatta.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.f10.amatta.R
import com.f10.amatta.adapter.ViewPagerAdapter
import com.f10.amatta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var cardList: ArrayList<String> = arrayListOf("123", "222", "333")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.cardList.adapter = ViewPagerAdapter(this.cardList)
        binding.cardList.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}