package project.st991493546.baran.weight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weightitems.view.*
import project.st991493546.baran.R
import project.st991493546.baran.database.WeightListItems

class WeightView (private val weightList: List <WeightListItems>) : RecyclerView.Adapter <WeightView.MyViewHolder>(){


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.txtName
        val date: TextView = itemView.txtDate
        val reps: TextView = itemView.txtReps
        val sets: TextView = itemView.txtSets
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.weightitems,  parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = weightList[position]

        holder.name.text = currentItem.name
        holder.date.text = currentItem.date
        holder.reps.text = currentItem.reps
        holder.sets.text = currentItem.sets
    }

    override fun getItemCount() = weightList.size
}