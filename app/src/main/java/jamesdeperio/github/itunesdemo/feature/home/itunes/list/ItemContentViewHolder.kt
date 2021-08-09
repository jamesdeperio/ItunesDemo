package jamesdeperio.github.itunesdemo.feature.home.itunes.list

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import jamesdeperio.github.itunesdemo.base.list.ViewHolder
import jamesdeperio.github.itunesdemo.databinding.ItemListContentBinding

class ItemContentViewHolder(
        private val adapter: ContentContract
) : ViewHolder() {

    //region VIEW BINDING
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(view: View, position: Int) {
        val binding = ItemListContentBinding.bind(view)
        val content = adapter.contents[position]

        binding.tvTrackerName.text = content.data!!.trackName
        binding.tvPrice.text = "Price:${content.data.trackPrice}"
        Glide.with(view)
            .load(content.data.artworkUrl100)
           .into(binding.ivMedia)

    }
    //endregion
}