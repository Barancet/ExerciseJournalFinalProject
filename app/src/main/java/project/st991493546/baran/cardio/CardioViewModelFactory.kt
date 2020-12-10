package project.st991493546.baran.cardio

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import project.st991493546.baran.database.ApplicationDatabase
import project.st991493546.baran.database.CardioDao

class CardioViewModelFactory (private val dataSource: CardioDao, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardioViewModel::class.java)) {
            return CardioViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}