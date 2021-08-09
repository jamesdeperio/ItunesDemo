package jamesdeperio.github.itunesdemo.feature.home.itunes.list

import jamesdeperio.github.itunesdemo.base.list.Adapter

class ItunesContentAdapter: Adapter(), ContentContract {
    companion object {
        private const val VIEWHOLDER_HEADER_INDEX = 0
        private const val VIEWHOLDER_CONTENT_INDEX = 1
    }

    override val contents: MutableList<ItunesContent> = ArrayList()

    override fun getItemCount(): Int = 10//contents.size

    override fun viewTypeCondition(position: Int): Int = when {
        contents[position].isHeader -> VIEWHOLDER_HEADER_INDEX
        else -> VIEWHOLDER_CONTENT_INDEX
    }
}

data class ItunesContent(
    val id: Int,
    val imagePath: String? = null,
    val genre:String,
    val price:String,
    val isHeader:Boolean = false
)

