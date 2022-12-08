package com.f10.amatta.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.f10.amatta.R
import com.f10.amatta.adapter.ViewPagerAdapter
import com.f10.amatta.databinding.ActivityMainBinding
import com.f10.amatta.decoration.HorizontalMarginItemDecoration
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var dataList: ArrayList<String> = arrayListOf("123", "222", "333")
    private val viewPagerAdapter: ViewPagerAdapter = ViewPagerAdapter(this.dataList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        setViewPager();
        setIndicator();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    private fun setViewPager(){
        linkAdapter()
        setViewPagerPreview()
        setViewPagerOverScrollMode()
    }

    private fun linkAdapter() {
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.apply { offscreenPageLimit = 3 }
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
        binding.viewPager.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            binding.root.context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.viewPager.addItemDecoration(itemDecoration)
    }

    private fun setViewPagerOverScrollMode(){
        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun setIndicator() {
        binding.viewPagerIndicator.setViewPager(binding.viewPager)
        viewPagerAdapter.registerAdapterDataObserver(binding.viewPagerIndicator.adapterDataObserver)
    }
}