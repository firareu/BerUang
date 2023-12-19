package com.capstone.beruang.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.capstone.beruang.R
import com.capstone.beruang.ui.register.RegisterActivity
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class SlidePage : AppCompatActivity() {
    private var name = ""
    private val vp by lazy {
        findViewById<ViewPager2>(R.id.slideVP)
    }
    private lateinit var switchButton: ViewPager2.OnPageChangeCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        //for Access Adapter
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_page)
        val dotsSlide = findViewById<DotsIndicator>(R.id.dots_indicator)
        val pagerAdapter = ViewPageAdapter(this) { name = it.toString() }
        vp.adapter = pagerAdapter
        dotsSlide.setViewPager2(vp)
        //Show button finish in Slide 4 and button next in another slide
        val btnFinish = findViewById<Button>(R.id.button_to_regis)
        val btnNext = findViewById<Button>(R.id.button_next)
        switchButton = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 3) {
                    btnFinish.visibility = View.VISIBLE
                    btnNext.visibility = View.GONE
                } else {
                    btnNext.visibility = View.VISIBLE
                    btnFinish.visibility = View.GONE
                }
            }
        }
        vp.registerOnPageChangeCallback(switchButton)
        //Button next used to swipe the slide until slide 4
        btnNext.setOnClickListener {
            if (vp.currentItem < 3) {
                vp.currentItem = vp.currentItem.plus(1)
            }
        }
        //Button finish used to finish all
        btnFinish.setOnClickListener {
            when {
                name != "" -> {
                    Intent(this, RegisterActivity::class.java)
                        .apply {
                            startActivity(this)
                        }
                }
            }
        }
    }
    //Used to destroy onPageChangeCallback. If it don't use it,it make memory leak
    override fun onDestroy() {
        super.onDestroy()
        vp.registerOnPageChangeCallback(switchButton)
    }
}