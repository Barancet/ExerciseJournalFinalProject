package project.st991493546.baran.database


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Date
//all done by Ebrahim
@Parcelize
@Entity(tableName = "weight_table")

data class WeightEntity
 (
 @PrimaryKey(autoGenerate = true)
 var id: Long,

 @ColumnInfo(name = "date")
 var date: String,

 @ColumnInfo(name = "weightType")
 var weightType: String,

 @ColumnInfo(name = "reps")
 var reps: Int,

 @ColumnInfo(name = "sets")
 var sets: Int
) : Parcelable