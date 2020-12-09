package project.st991493546.baran.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "cardio_table")

data class CardioEntity
    (@PrimaryKey(autoGenerate = true)
     var id: Long,

//     @ColumnInfo(name = "date")
//     var date: Date,

     @ColumnInfo(name = "cardioName")
     var cardioName: String,

     @ColumnInfo(name = "duration")
     var duration: Int,

     @ColumnInfo(name = "distance")
     var distance: Int
)