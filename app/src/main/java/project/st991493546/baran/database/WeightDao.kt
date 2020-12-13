package project.st991493546.baran.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeightDao {
    @Query("SELECT * from weight_table WHERE id = :key")
    suspend fun getOne(key: Long): WeightEntity?

    @Query("SELECT * from weight_table")
    suspend fun getAll(): WeightEntity?

    @Insert
    suspend fun insert(weight: WeightEntity)

    @Update
    suspend fun update(weight: WeightEntity)

    @Query("DELETE FROM weight_table Where id = :key")
    suspend fun delete(key: Long)


    @Query("SELECT * FROM weight_table")
    fun getAllRecordsLiveData(): LiveData<List<WeightEntity>>

    @Query("SELECT * from weight_table WHERE id = :id")
    suspend fun getOneRecord(id: Long): WeightEntity


}