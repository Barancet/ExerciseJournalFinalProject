package project.st991493546.baran.weight

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.st991493546.baran.database.WeightDao
import project.st991493546.baran.database.WeightEntity

class WeightViewModel(private val weightDao: WeightDao, application: Application) :
    AndroidViewModel(application) {


    val readAllData: LiveData<List<WeightEntity>> = weightDao.getAllRecordsLiveData()

    suspend fun updateWeightCoroutine(weight: WeightEntity) {
        weightDao.update(weight)
    }

    fun updateWeight(weight: WeightEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            updateWeightCoroutine(weight)
        }
    }

    var weight = weightDao.getAllRecordsLiveData()


    suspend fun weightItems(): WeightEntity? {
        var weightList = weightDao.getAll()
        return weightList
    }

    fun insertIntoDB(name: String, date: String, reps: Int, sets: Int) {
        val weight = WeightEntity(
            0,
            weightType = name,
            reps = reps,
            sets = sets,
            date = date
            //the date is Int. so get the value of text box which is date and format it. the number shoud be DDMMYYYY
        )

        viewModelScope.launch {
            val newWeight = WeightEntity(
                weight.id,
                weight.date,
                weight.weightType,
                weight.reps,
                weight.sets
            )
            insert(newWeight)
        }
    }

    private suspend fun insert(weight: WeightEntity) {
        withContext(Dispatchers.IO) {
            weightDao.insert(weight)
        }
    }

    fun deleteById(id: Long) {
        viewModelScope.launch {
            deleteByIdSuspend(id)

        }
    }

    private suspend fun deleteByIdSuspend(id: Long) {
        return withContext(Dispatchers.IO) {
            weightDao.delete(id)
        }
    }

}