package project.st991493546.baran.weight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardioitems.view.*
import kotlinx.android.synthetic.main.weightitems.view.*
import kotlinx.android.synthetic.main.weightitems.view.txtDate
import kotlinx.android.synthetic.main.weightitems.view.txtName
import kotlinx.android.synthetic.main.weightitems.view.txtReps
import kotlinx.android.synthetic.main.weightitems.view.txtSets
import project.st991493546.baran.R
import project.st991493546.baran.cardio.CardioDirections
import project.st991493546.baran.cardio.CardioViewAdapter
import project.st991493546.baran.database.CardioEntity
import project.st991493546.baran.database.WeightEntity
import project.st991493546.baran.database.WeightListItems

class WeightView : RecyclerView.Adapter <WeightView.ViewHolder>(){


    private var weightList = emptyList<WeightEntity>()

//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val name: TextView = itemView.txtName
//        val date: TextView = itemView.txtDate
//        val reps: TextView = itemView.txtReps
//        val sets: TextView = itemView.txtSets
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weightitems,  parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = weightList[position]
        holder.view.IdWeight.text = currentItem?.id.toString()
        holder.view.txtName.text = currentItem.weightType
        holder.view.txtDate.text = currentItem.date
        holder.view.txtReps.text = currentItem.reps.toString()
        holder.view.txtSets.text = currentItem.sets.toString()


        holder.view.relativeLayoutWeight.setOnClickListener{
            val action = WeightDirections.actionWeightToWeightEdit(currentItem)
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount() : Int  = weightList.size


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(weight : List<WeightEntity>){
        this.weightList = weight
        notifyDataSetChanged()
    }

}