package project.st991493546.baran.weight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weightitems.view.*
import project.st991493546.baran.R
import project.st991493546.baran.database.WeightEntity

class WeightView : RecyclerView.Adapter<WeightView.ViewHolder>() {

    private var weightList = emptyList<WeightEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weightitems, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = weightList[position]
        holder.view.IdWeight.text = currentItem?.id.toString()
        holder.view.txtName.text = currentItem.weightType
        holder.view.txtDate.text = currentItem.date
        holder.view.txtReps.text = currentItem.reps.toString()
        holder.view.txtSets.text = currentItem.sets.toString()

        holder.view.relativeLayoutWeight.setOnClickListener {
            val action = WeightDirections.actionWeightToWeightEdit(currentItem)
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = weightList.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(weight: List<WeightEntity>) {
        this.weightList = weight
        notifyDataSetChanged()
    }
}