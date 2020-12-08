package project.st991493546.baran.weight

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import project.st991493546.baran.database.WeightListItems

class WeightViewModel : ViewModel() {

    private var ourList = generateList(1)

    private fun generateList(size : Int): List<WeightListItems> {
        val list = ArrayList<WeightListItems>()

        val item = WeightListItems("Bench", "12/05/2020","10 mins", "2km")
        val item2 = WeightListItems("Shoulder Press", "12/05/2020","20 mins", "5km")
        val item3 = WeightListItems("Squats", "12/06/2020","33 mins", "1km")

        list += item
        list += item2
        list += item3

        return list
    }

    fun getOurList(): List<WeightListItems>{
        return ourList
    }

}