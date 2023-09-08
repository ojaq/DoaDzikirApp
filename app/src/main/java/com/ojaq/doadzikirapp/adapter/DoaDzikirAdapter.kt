package com.ojaq.doadzikirapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ojaq.doadzikirapp.R
import com.ojaq.doadzikirapp.model.DoaDzikirItem

//Adapter is a subclass from RecyclerView which takes responsibility
//for providing views that displays items in a data set
class DoaDzikirAdapter : RecyclerView.Adapter<DoaDzikirAdapter.DzikirViewHolder>() {
    private val listData = ArrayList<DoaDzikirItem>()

    //set data from data source to Adapter
    fun setData(list: List<DoaDzikirItem>){
        listData.clear()
        listData.addAll(list)
    }

    //ViewHolder takes responsibility for initialize  item from layout
    //in this
    inner class DzikirViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemTitle = view.findViewById<TextView>(R.id.item_title)
        val itemArabic = view.findViewById<TextView>(R.id.item_arabic)
        val itemTranslate = view.findViewById<TextView>(R.id.item_translate)
    }

    // onCreateViewHolder provides layout to be used by ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DzikirViewHolder {
        return DzikirViewHolder(
            //LayoutInflater is a class to inflate a layout/view
            LayoutInflater.from(parent.context).inflate(R.layout.item_doa,parent, false)
        )
    }

    //getItemCount is counting and setting the size of data set will be display on RecyclerView
    override fun getItemCount() = listData.size

    //onBindViewHolder distributes data in their position on item view
    override fun onBindViewHolder(holder: DzikirViewHolder, position: Int) {
        holder.apply {
            itemTitle.text = listData[position].title
            itemArabic.text = listData[position].arabic
            itemTranslate.text = listData[position].translate
        }
    }
}