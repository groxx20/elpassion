package com.elpassion.assignment.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.elpassion.assignment.R
import com.elpassion.assignment.model.ItemList
import com.elpassion.assignment.ui.main.MainView

/**
 * Created by pavel on 10/2/18.
 */

class ItemsAdapter(private val itemsList: List<ItemList>, private val context: Context, private val listener: MainView): RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        holder?.card?.setOnClickListener{ listener.goNext(itemsList[position].name) }

        if(itemsList[position].isPerson){
            holder?.txtName?.text = itemsList[position].login
            holder?.imgItem?.setImageDrawable(context.resources.getDrawable(R.drawable.user_icon))
        }
        else{
            holder?.txtName?.text = itemsList[position].name
            holder?.imgItem?.setImageDrawable(context.resources.getDrawable(R.drawable.folder_icon))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.name)!!
        val card = itemView.findViewById<CardView>(R.id.card)!!
        val imgItem = itemView.findViewById<ImageView>(R.id.imgItem)

    }

}