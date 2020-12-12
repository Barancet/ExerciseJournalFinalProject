package project.st991493546.baran.weight

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.st991493546.baran.database.*
import project.st991493546.baran.formatCardio

class WeightViewModel(private val weightDao: WeightDao, application: Application) : AndroidViewModel(application) {

    var weightLiveData = MutableLiveData<WeightEntity?>()




    val readAllData : LiveData<List<WeightEntity>> = weightDao.getAllRecordsLiveData()

    suspend fun updateWeightCoroutine(weight : WeightEntity){
        weightDao.update(weight)
    }

    fun updateWeight(weight : WeightEntity){
        viewModelScope.launch(Dispatchers.IO) {
            updateWeightCoroutine(weight)
        }
    }


    var weight = weightDao.getAllRecordsLiveData()



    init {
        initializeWeightLiveData()
    }

    private fun initializeWeightLiveData() {
        viewModelScope.launch {
            weightLiveData.value = weightItems()

        }
    }



    fun displayAll(){
        viewModelScope.launch {
            weightLiveData.value = weightItems()
        }
    }

    suspend fun weightItems() : WeightEntity? {
        var weightList = weightDao.getAll()
        return weightList
    }

    fun insertIntoDB(name : String, date : String, reps : Int, sets: Int) {
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
        withContext(Dispatchers.IO){
            weightDao.insert(weight)
        }

    }
    fun deleteById(id : Long) {
        viewModelScope.launch {
            deleteByIdSuspend(id)
            weightLiveData.value = weightItems()
        }
    }

    //
    private suspend fun deleteByIdSuspend(id : Long) {
        return withContext(Dispatchers.IO) {
            weightDao.delete(id)
        }
    }

}