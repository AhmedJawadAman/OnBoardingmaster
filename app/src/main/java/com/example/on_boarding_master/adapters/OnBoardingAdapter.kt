package com.example.on_boarding_master.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.on_boarding_master.OnBoarding
import com.example.on_boarding_master.databinding.OnBoardingLayoutBinding

class OnBoardingAdapter(val list: List<OnBoarding>) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingHolder>() {

    inner class OnBoardingHolder(val binding: OnBoardingLayoutBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingHolder =
        OnBoardingHolder(
            OnBoardingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardingHolder, position: Int) {
        holder.binding.run {
            Glide.with(holder.itemView).load(list[position].image).into(image)
            //headline.text = holder.itemView.context.getString(list[position].title)
          //  description.text = holder.itemView.context.getString(list[position].description)
        }
    }

}