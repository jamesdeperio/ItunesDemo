package jamesdeperio.github.itunesdemo.base.list
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jamesdeperio.github.itunesdemo.base.list.HasAdapterContract


abstract class ViewHolder : HasAdapterContract.Holder {
    lateinit var viewHolder: RecyclerView.ViewHolder
    var layout: Int = 0
    override fun setView(view: View) {
        viewHolder =  Holder(view)
    }
    override fun setContentView(layoutID: Int) {
        layout = layoutID
    }
    inner class Holder(view: View):  RecyclerView.ViewHolder(view)
}