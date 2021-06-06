package com.juandelarosa.tmobile.ui

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juandelarosa.domain.entities.*
import com.juandelarosa.tmobile.app.LayoutUtils
import com.juandelarosa.tmobile.databinding.ActivityMainBinding
import com.juandelarosa.tmobile.databinding.ImageCardItemBinding
import com.juandelarosa.tmobile.databinding.TextCardItemBinding
import com.juandelarosa.tmobile.databinding.TextDescriptionCardItemBinding


class CardAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var cards: ArrayList<Cards> = arrayListOf()
    fun setData(list: List<Cards>){
        if(!list.isNullOrEmpty()){
            if(cards.isNullOrEmpty())
                cards = list as ArrayList<Cards>
            else {
                list.forEach {
                    if(!cards.contains(it))
                        cards.add(it)
                }
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(cards[position].card_type){
            CardType.Text -> 1
            CardType.TitleDescription -> 2
            CardType.Image -> 3
            CardType.Unknown -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                TextViewHolder(TextCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            2 -> {
                TitleDetailViewHolder(TextDescriptionCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            3 -> {
                ImageViewHolder(ImageCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> TextViewHolder(TextCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(cards[position].card_type){
            CardType.Text -> {
                val textHolder = holder as TextViewHolder
                val cardText = cards[position].card as TextCard
                LayoutUtils.textWithStyle(textHolder.binding.title, cardText.title)
            }
            CardType.TitleDescription -> {
                val titleDetailHolder = holder as TitleDetailViewHolder
                val cardTitleDetail = cards[position].card as TitleDescriptionCard
                LayoutUtils.textWithStyle(titleDetailHolder.binding.title, cardTitleDetail.title)
                LayoutUtils.textWithStyle(titleDetailHolder.binding.description, cardTitleDetail.description)
            }
            CardType.Image -> {
                val imageHolder = holder as ImageViewHolder
                val cardImage = cards[position].card as ImageCard
                LayoutUtils.textWithStyle(imageHolder.binding.title, cardImage.title)
                LayoutUtils.textWithStyle(imageHolder.binding.description, cardImage.description)
                LayoutUtils.setImage(imageHolder.binding.image,cardImage.image.url)
                imageHolder.binding.root.setOnClickListener {

                }
            }
        }
    }



    override fun getItemCount(): Int {
        return cards.count()
    }

    class TextViewHolder(val binding: TextCardItemBinding): RecyclerView.ViewHolder(binding.root)
    class ImageViewHolder(val binding: ImageCardItemBinding): RecyclerView.ViewHolder(binding.root)
    class TitleDetailViewHolder(val binding: TextDescriptionCardItemBinding): RecyclerView.ViewHolder(binding.root)
}