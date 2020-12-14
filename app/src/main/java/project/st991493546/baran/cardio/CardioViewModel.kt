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

    // readAllData - done by Alkesh
    val readAllData: LiveData<List<CardioEntity>> = cardioDao.getAllRecordsLiveData()


    // updateCardioCoroutine and updateCardio done by Ebrahim Zhalehsani
    fun updateCardio(cardio: CardioEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCardioCoroutine(cardio)
        }
    }
    suspend fun updateCardioCoroutine(cardio: CardioEntity) {
        cardioDao.update(cardio)
    }



    // insertIntoDB and insert were done by Baran Cetin
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
            insert(cardio)
        }
    }
    private suspend fun insert(cardio: CardioEntity) {
        withContext(Dispatchers.IO) {
            cardioDao.insert(cardio)
        }
    }


    // deleteById and deleteByIdSuspend done by Alkesh Sandal
    fun deleteById(id: Long) {
        viewModelScope.launch {
            deleteByIdSuspend(id)

        }
    }
    private suspend fun deleteByIdSuspend(id: Long) {
        return withContext(Dispatchers.IO) {
            cardioDao.delete(id)
        }
    }
}