package com.example.on_boarding_master

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.example.on_boarding_master.adapters.OnBoardingAdapter
import com.example.on_boarding_master.databinding.ActivityOnBoardingBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var dotsIndicator: DotsIndicator
    private var currentPagePosition = MutableLiveData(0)
    private lateinit var mAdapter: OnBoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        mAdapter = OnBoardingAdapter(listOfOnBoardings)
        dotsIndicator = binding.wormDotsIndicator
        binding.run {
            viewpager2.adapter = mAdapter
            dotsIndicator.setViewPager2(viewpager2)

            viewpager2.setPageTransformer(ZoomOutPageTransformer())

            viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    // println(state)
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    currentPagePosition.value = position
                }

            })


            currentPagePosition.observe(this@OnBoardingActivity) {
                setTint(it)
                btnNext.visibility = if (it >= 2) View.VISIBLE else View.GONE
            }


        }
    }



        private fun ActivityOnBoardingBinding.setTint(position: Int) {
            val selectedColor = ContextCompat.getColor(root.context, R.color.green)

            when (position) {
                0 -> {
                    //dotsIndicator.setDotIndicatorColor(selectedColor)
                    binding.headline.text = getString(R.string.near_by_places)
                    binding.description.text = getString(R.string.near_by_places_description)
                }
                1 -> {
                  //  dotsIndicator.setDotIndicatorColor(selectedColor)
                    binding.headline.text = getString(R.string.route_finder)
                    binding.description.text = getString(R.string.route_finder_description)
                }
                2 -> {
                  //  dotsIndicator.setDotIndicatorColor(selectedColor)
                    binding.headline.text = getString(R.string.world_tour)
                    binding.description.text = getString(R.string.world_tour_description)
                }

            }
        }

}
data class OnBoarding(val title: Int, val image: Int, val description: Int)

val listOfOnBoardings = listOf(
    OnBoarding(
        R.string.near_by_places,
        R.drawable.nearby_on_boarding,
        R.string.near_by_places_description
    ),
    OnBoarding(
        R.string.route_finder,
        R.drawable.route_finder_onboarding,
        R.string.route_finder_description
    ),
    OnBoarding(
        R.string.world_tour,
        R.drawable.tour_onboarding,
        R.string.world_tour_description
    )

)