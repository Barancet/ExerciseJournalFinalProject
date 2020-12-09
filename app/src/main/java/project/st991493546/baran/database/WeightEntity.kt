package project.st991493546.baran.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "weight_table")

data class WeightEntity
    (@PrimaryKey(autoGenerate = true)
     var id: Long,

//     @ColumnInfo(name = "date")
//     var date: Date,

     @ColumnInfo(name = "weightType")
     var weightType: String,

     @ColumnInfo(name = "reps")
     var reps: Int,

     @ColumnInfo(name = "sets")
     var sets: Int
)