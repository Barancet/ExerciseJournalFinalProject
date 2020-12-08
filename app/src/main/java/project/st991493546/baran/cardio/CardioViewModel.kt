package project.st991493546.baran.cardio

import androidx.lifecycle.ViewModel
import project.st991493546.baran.database.CardioListItems

class CardioViewModel : ViewModel() {

    private var ourList = generateList(1)

    private fun generateList(size : Int): List<CardioListItems> {
        val list = ArrayList<CardioListItems>()
        val item = CardioListItems("Running", "12/05/2020","10 mins", "2km")
        val item2 = CardioListItems("Biking", "12/05/2020","20 mins", "5km")
        val item3 = CardioListItems("Swimming", "12/06/2020","33 mins", "1km")
        list += item
        list += item2
        list += item3

        return list
    }

    fun getOurList() : List<CardioListItems>{
        return ourList
    }
}