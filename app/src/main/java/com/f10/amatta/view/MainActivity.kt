package com.f10.amatta.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.f10.amatta.R
import com.f10.amatta.adapter.ViewPagerAdapter
import com.f10.amatta.databinding.ActivityMainBinding
import com.f10.amatta.decoration.HorizontalMarginItemDecoration
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var cardList: ArrayList<String> = arrayListOf("123", "222", "333")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        setViewPager();
    }

    private fun setViewPager(){
        linkAdapter()
        setViewPagerPreview()
    }

    private fun linkAdapter() {
        binding.cardList.adapter = ViewPagerAdapter(this.cardList)
        binding.cardList.apply { offscreenPageLimit = 3 }
    }

    private fun setViewPagerPreview() {
        val currentItemHorizontalMarginPx = resources
            .getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * abs(position))
            page.alpha = 0.25f + (1 - abs(position))
        }
        binding.cardList.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            binding.root.context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.cardList.addItemDecoration(itemDecoration)
    }
}