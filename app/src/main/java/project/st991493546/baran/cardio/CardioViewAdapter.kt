package project.st991493546.baran.cardio

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardioitems.view.*
import project.st991493546.baran.R
import project.st991493546.baran.database.CardioEntity


class CardioViewAdapter : RecyclerView.Adapter <CardioViewAdapter.ViewHolder>(){

    private var cardioList = emptyList<CardioEntity>()

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

        holder.view.Id.text = currentItem?.id.toString()
        holder.view.txtName.text = currentItem?.cardioName
        holder.view.txtDate.text = currentItem?.date
        holder.view.txtSets.text = currentItem?.distance.toString()
        holder.view.txtReps.text = currentItem?.duration.toString()


        holder.view.relativeLayout.setOnClickListener{
            val action = CardioDirections.actionCardioToCardioEdit(currentItem)
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount() : Int = cardioList.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(cardio : List<CardioEntity>){
        this.cardioList = cardio
        notifyDataSetChanged()
    }

}