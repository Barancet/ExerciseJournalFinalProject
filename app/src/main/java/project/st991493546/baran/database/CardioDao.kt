package project.st991493546.baran.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CardioDao {
    @Query("SELECT * from cardio_table WHERE id = :key")
    fun getOne(key: Long): CardioEntity?

    @Query("SELECT * from cardio_table")
    fun getAll(): List<CardioEntity?>

    @Insert
    fun insert(cardio: CardioEntity)

    @Update
    fun update(cardio: CardioEntity)

    @Query("DELETE FROM cardio_table Where id = :key")
    fun delete(key: Long)
}