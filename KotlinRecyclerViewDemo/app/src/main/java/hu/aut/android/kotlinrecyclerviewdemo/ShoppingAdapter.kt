package hu.aut.android.kotlinrecyclerviewdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import hu.aut.android.kotlinrecyclerviewdemo.data.ShoppingItem

class ShoppingAdapter : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>(){

    private val items = mutableListOf<ShoppingItem>(
            ShoppingItem("milk", 200),
            ShoppingItem("car", 4000),
            ShoppingItem("beer", 1)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.row_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, price) = items[holder.adapterPosition]
        holder.tvName?.text = name
        holder.tvPrice?.text = price.toString()
    }

    fun addItem(item: ShoppingItem) {
        items += item
        notifyItemInserted(items.lastIndex)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView?.findViewById<TextView>(R.id.tvName)
        val tvPrice = itemView?.findViewById<TextView>(R.id.tvPrice)
    }

}