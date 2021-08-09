package jamesdeperio.github.itunesdemo.feature.home.itunes.list

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import jamesdeperio.github.itunesdemo.R
import jamesdeperio.github.itunesdemo.base.list.ViewHolder
import jamesdeperio.github.itunesdemo.base.setOnThrottleClickListener
import jamesdeperio.github.itunesdemo.databinding.ItemListContentBinding
import jamesdeperio.github.itunesdemo.feature.home.itunes.ItunesContentViewModel

/*
home content layout
 */
class ItemContentViewHolder(
    private val adapter: ContentContract,
    private val viewModel: ItunesContentViewModel
) : ViewHolder() {

    //region VIEW BINDING
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(view: View, position: Int) {
        val binding = ItemListContentBinding.bind(view)
        val content = adapter.contents[position]

        binding.tvTrackerName.text = content.data!!.trackName
        binding.tvPrice.text = "Price: $${content.data.trackPrice}"
        Glide.with(view)
            .load(content.data.artworkUrl100)
            .error(R.drawable.placeholder)
           .into(binding.ivMedia)

        binding.itemView.setOnThrottleClickListener {
            viewModel.loadDetailPage(content= content)
        }
    }
    //endregion
}