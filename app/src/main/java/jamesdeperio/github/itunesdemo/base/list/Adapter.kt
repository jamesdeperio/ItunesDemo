package jamesdeperio.github.itunesdemo.base.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/*
Base Class for Recycler Adapter for creating multiple viewholder
 */
abstract class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    HasAdapterContract.Adapter {
    private val pocketViewHolderList = ArrayList<ViewHolder>()
    private var selectedLayout: Int = 0

    /*
    Check what view holder to be shown
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        (0 until pocketViewHolderList.size)
            .filter {  viewType==it }
            .forEach {
                pocketViewHolderList[it].setView(LayoutInflater.from(parent.context).inflate(pocketViewHolderList[it].layout, parent, false))
                return pocketViewHolderList[it].viewHolder
            }
        return pocketViewHolderList[0].viewHolder
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
            = pocketViewHolderList[selectedLayout].onBindViewHolder(holder.itemView,position)

    override fun getItemViewType(position: Int): Int {
        selectedLayout = viewTypeCondition(position)
        return selectedLayout
    }

    override fun viewTypeCondition(position: Int): Int = 0

    override fun addViewHolder(viewHolder: ViewHolder) {
        pocketViewHolderList.add(viewHolder)
    }


}