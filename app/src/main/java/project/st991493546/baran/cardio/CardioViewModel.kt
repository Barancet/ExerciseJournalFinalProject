package project.st991493546.baran.cardio

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import project.st991493546.baran.database.ApplicationDatabase
import project.st991493546.baran.database.CardioDao
import project.st991493546.baran.database.CardioEntity
import project.st991493546.baran.database.CardioListItems
import project.st991493546.baran.formatCardio
import java.text.SimpleDateFormat
import java.util.*

class CardioViewModel(private val cardioDao: CardioDao, application: Application) : AndroidViewModel(application) {

    var cardioLiveData = MutableLiveData<CardioEntity?>()
    //private var ourList = generateList(1)
    //private var list = getCardioFromDB()
    //private var cardio = cardioDao.getAll()
    //var list = cardio
    private var listOfCardioNames = listOf("Running", "Biking", "Swimming")
    private var listOfDates = listOf("12/05/2020", "12/06/2020", "12/07/2020")
    private var listOfDistances = listOf(2, 5, 10)
    private var listOfDuration = listOf(10, 15, 30)

    private var cardio = cardioDao.getAllRecordsLiveData()

    var cardioString = Transformations.map(cardio) { cardio ->
        formatCardio(cardio, application.resources)
    }

    init {
        initializeCardioLiveData()
    }

    private fun initializeCardioLiveData() {
        viewModelScope.launch {
            cardioLiveData.value = cardioItems()
        }
    }

    fun displayAll(){
        viewModelScope.launch {
            cardioLiveData.value = cardioItems()
        }
    }

    suspend fun cardioItems(): CardioEntity? {
        var cardioList = cardioDao.getAll()
        return cardioList
    }

    fun insertIntoDB() {
        val cardio = CardioEntity(
            0,
            cardioName = listOfCardioNames[(0..2).random()],
            distance = listOfDistances[(0..2).random()],
            duration = listOfDuration[(0..2).random()],
            date = SimpleDateFormat("dd-MM-yyyy").format(Date())
        )

        viewModelScope.launch {
            val newCardio = CardioEntity(
                cardio.id,
                cardio.date,
                cardio.cardioName,
                cardio.duration,
                cardio.distance
            )
            insert(newCardio)
        }


    }

    private suspend fun insert(cardio: CardioEntity) {
        withContext(Dispatchers.IO){
            cardioDao.insert(cardio)
            Log.i("Test", "($cardio)")
        }

    }

}


//        var list: List<CardioEntity?>
//        list = emptyList()
//        doAsync {
//            list = cardioDao.getAll()
//        }
//        return list




//            doAsync {
//                cardioDao.insert(cardio)
//                uiThread {
//                    Toast.makeText(getApplication(), "Record inserted", Toast.LENGTH_LONG).show()
//                    Log.i("CardioViewModel", "inserted ${cardioDao}")
//                }
//            }



//    fun displayCardio() {
//        viewModelScope.launch {
//
//
//
//        }
//
//    }
//
//
//
//    private suspend fun getCardioFromDB() : List<CardioEntity>{
//
//        return withContext(Dispatchers.IO) {
//            cardio = cardioDao.getAll()
//        }
//    }
//
//
// }



//    private fun generateList(size : Int): List<CardioListItems> {
//        val list = ArrayList<CardioListItems>()
//        val item = CardioListItems("Running", "12/05/2020","10 mins", "2km")
//        val item2 = CardioListItems("Biking", "12/05/2020","20 mins", "5km")
//        val item3 = CardioListItems("Swimming", "12/06/2020","33 mins", "1km")
//        val item4 = CardioListItems("Running", "12/05/2020","10 mins", "2km")
//        val item5 = CardioListItems("Biking", "12/05/2020","20 mins", "5km")
//        val item6 = CardioListItems("Swimming", "12/06/2020","33 mins", "1km")
//        val item7 = CardioListItems("Running", "12/05/2020","10 mins", "2km")
//        val item8 = CardioListItems("Biking", "12/05/2020","20 mins", "5km")
//        val item9 = CardioListItems("Swimming", "12/06/2020","33 mins", "1km")
//
//        list += item
//        list += item2
//        list += item3
//        list += item4
//        list += item5
//        list += item6
//        list += item7
//        list += item8
//        list += item9
//
//        return list
//    }
//
//    fun getOurList() : List<CardioListItems>{
//        return ourList
//    }