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
import kotlin.collections.ArrayList

class CardioViewModel(private val cardioDao: CardioDao, application: Application) : AndroidViewModel(application) {

    var cardioLiveData = MutableLiveData<CardioEntity?>()




    val readAllData : LiveData<List<CardioEntity>> = cardioDao.getAllRecordsLiveData()

    suspend fun updateCardioCoroutine(cardio : CardioEntity){
        cardioDao.update(cardio)
    }

    fun updateCardio(cardio : CardioEntity){
        viewModelScope.launch(Dispatchers.IO) {
            updateCardioCoroutine(cardio)
        }
    }


    var cardio = cardioDao.getAllRecordsLiveData()




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

    suspend fun cardioItems() : CardioEntity? {
        var cardioList = cardioDao.getAll()
        return cardioList
    }

    fun insertIntoDB(name : String, date : String, duration : Int, distance: Int) {
        val cardio = CardioEntity(
            0,
            cardioName = name,
            distance = distance,
            duration = duration,
            date = date
            //the date is Int. so get the value of text box which is date and format it. the number shoud be DDMMYYYY
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
    fun deleteById(id : Long) {
        viewModelScope.launch {
            deleteByIdSuspend(id)
            cardioLiveData.value = cardioItems()
        }
    }

    //
    private suspend fun deleteByIdSuspend(id : Long) {
        return withContext(Dispatchers.IO) {
            cardioDao.delete(id)
        }
    }


}

//private var ourList = generateList(1)
//private var list = getCardioFromDB()
//private var cardio = cardioDao.getAll()
//var list = cardio

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