package project.st991493546.baran.cardio

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardioitems.view.*
import project.st991493546.baran.R
import project.st991493546.baran.database.CardioEntity


class CardioViewAdapter (private val cardioList: List <CardioEntity?>) : RecyclerView.Adapter <CardioViewAdapter.ViewHolder>(){


//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val name: TextView = itemView.text_view_1
//        val date: TextView = itemView.txtDate
//        val distance: TextView = itemView.txtDistance
//        val duration: TextView = itemView.txtDuration
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardioitems,  parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = cardioList[position]
        Log.i("CardioAdapter", "$currentItem")

        holder.view.text_view_1.text = currentItem?.cardioName
        holder.view.txtDate.text = currentItem?.date
        holder.view.txtDistance.text = currentItem?.distance.toString()
        holder.view.txtDuration.text = currentItem?.duration.toString()
    }

    override fun getItemCount() = cardioList.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}