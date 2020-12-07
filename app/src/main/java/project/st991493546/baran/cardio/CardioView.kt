package project.st991493546.baran.cardio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardioitems.view.*
import project.st991493546.baran.R

import project.st991493546.baran.database.CardioListItems


class CardioView (private val cardioList: List <CardioListItems>) : RecyclerView.Adapter <CardioView.MyViewHolder>(){


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.text_view_1
        val date: TextView = itemView.txtDate
        val distance: TextView = itemView.txtDistance
        val duration: TextView = itemView.txtDuration
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cardioitems,  parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = cardioList[position]

        holder.name.text = currentItem.name
        holder.date.text = currentItem.date
        holder.distance.text = currentItem.distance
        holder.duration.text = currentItem.duration
    }

    override fun getItemCount() = cardioList.size


}