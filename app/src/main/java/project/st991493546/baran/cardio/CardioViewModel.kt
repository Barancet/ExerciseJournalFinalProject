package project.st991493546.baran.cardio

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.st991493546.baran.database.CardioDao
import project.st991493546.baran.database.CardioEntity


class CardioViewModel(private val cardioDao: CardioDao, application: Application) :
    AndroidViewModel(application) {

    var cardioLiveData = MutableLiveData<CardioEntity?>()
    val readAllData: LiveData<List<CardioEntity>> = cardioDao.getAllRecordsLiveData()

    suspend fun updateCardioCoroutine(cardio: CardioEntity) {
        cardioDao.update(cardio)
    }

    fun updateCardio(cardio: CardioEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCardioCoroutine(cardio)
        }
    }

    var cardio = cardioDao.getAllRecordsLiveData()

    init {
        initializeCardioLiveData()
    }

    private fun initializeCardioLiveData() {
        viewModelScope.launch {
            cardioLiveData.value = cardioItems()
        }
    }

    suspend fun cardioItems(): CardioEntity? {
        var cardioList = cardioDao.getAll()
        return cardioList
    }

    fun insertIntoDB(name: String, date: String, duration: Int, distance: Int) {
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
        withContext(Dispatchers.IO) {
            cardioDao.insert(cardio)
            Log.i("Test", "($cardio)")
        }
    }

    fun deleteById(id: Long) {
        viewModelScope.launch {
            deleteByIdSuspend(id)
            cardioLiveData.value = cardioItems()
        }
    }

    private suspend fun deleteByIdSuspend(id: Long) {
        return withContext(Dispatchers.IO) {
            cardioDao.delete(id)
        }
    }
}