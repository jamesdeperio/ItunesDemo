package jamesdeperio.github.itunesdemo.feature.home.itunes.list

import android.annotation.SuppressLint
import android.view.View
import jamesdeperio.github.itunesdemo.base.list.ViewHolder
import jamesdeperio.github.itunesdemo.databinding.ItemListHeaderContentBinding

/*
home header layout
 */
class HeaderContentViewHolder(
        private val adapter: ContentContract
) : ViewHolder() {

    //region VIEW BINDING
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(view: View, position: Int) {
        val binding = ItemListHeaderContentBinding.bind(view)
        val content = adapter.contents[position]
        binding.tvTitle.text = content.genre

    }
    //endregion
}