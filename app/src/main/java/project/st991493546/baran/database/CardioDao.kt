package project.st991493546.baran.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CardioDao {
    @Query("SELECT * from cardio_table WHERE id = :key")
    suspend fun getOne(key: Long): CardioEntity?

    @Query("SELECT * from cardio_table")
    suspend fun getAll(): CardioEntity?

    @Insert
    suspend fun insert(cardio: CardioEntity)

    @Update
    fun update(cardio: CardioEntity)

    @Query("DELETE FROM cardio_table Where id = :key")
    fun delete(key: Long)



    @Query("SELECT * FROM cardio_table")
    fun getAllRecordsLiveData(): LiveData<List<CardioEntity>>
}