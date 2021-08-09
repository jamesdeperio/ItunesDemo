package jamesdeperio.github.itunesdemo.feature.home.itunes.list

import android.annotation.SuppressLint
import android.view.View
import jamesdeperio.github.itunesdemo.base.list.ViewHolder

class ItemContentViewHolder(
        private val adapter: ContentContract
) : ViewHolder() {

    //region VIEW BINDING
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(view: View, position: Int) {
       // val binding = ListItemHeaderContentBinding.bind(view)
       // val data = adapter.contents[position]
       // binding.tvRedditPrefix.text = data.subRedditPrefixed
      //   binding.tvSubreddit.text = data.subReddit

    }
    //endregion
}