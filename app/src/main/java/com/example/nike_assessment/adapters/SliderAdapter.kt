package com.example.nike_assessment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.nike_assessment.R
import com.example.nike_assessment.adapters.SliderAdapter.SliderViewHolder
import com.example.nike_assessment.model.Image

class SliderAdapter(private val sliderItems: List<Image?>) : RecyclerView.Adapter<SliderViewHolder>() {

    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        context = parent.context
        return SliderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position])
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userAvatar: ImageView = itemView.findViewById(R.id.imageSlide)
        private val imageTitle: TextView = itemView.findViewById(R.id.tv_name)
        fun setImage(item: Image?) {
            Glide.with(itemView.rootView)
                    .asBitmap()
                    .load(item?.media?.m).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(userAvatar)
            imageTitle.text = item?.title
        }

    }
}

