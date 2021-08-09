package jamesdeperio.github.itunesdemo.base.list

import android.view.View

internal interface HasAdapterContract {
    interface Adapter {
        fun viewTypeCondition(position: Int): Int
        fun addViewHolder(viewHolder: ViewHolder)
    }

    interface Holder {
        fun setContentView(layoutID: Int)
        fun setView(view: View)
        fun onBindViewHolder(view: View, position: Int)
    }
}