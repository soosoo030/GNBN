package org.techtown.gilnyangbomnyang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.diaryitem.view.*
import kotlinx.android.synthetic.main.fragment_c.view.*

class diaryItemAdapter : RecyclerView.Adapter<diaryItemAdapter.ViewHolder>(){
    var items = ArrayList<diaryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): diaryItemAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.diaryitem, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: diaryItemAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun setItem(item:diaryItem) {
            itemView.diary_date.text = item.diary_date
//            itemView.feed.isChecked = item.feed
//            itemView.water.isChecked = item.water
//            itemView.rescue.isChecked = item.rescue
            itemView.diary_content.text = item.diary_content

        }
    }
}