package project.st991493546.baran.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeightDao {
    @Query("SELECT * from weight_table WHERE id = :key")
    fun getOne(key: Long): WeightEntity?

    @Query("SELECT * from weight_table")
    fun getAll(): List<WeightEntity?>

    @Insert
    fun insert(weight: WeightEntity)

    @Update
    fun update(weight: WeightEntity)

    @Query("DELETE FROM weight_table Where id = :key")
    fun delete(key: Long)
}