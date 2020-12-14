package project.st991493546.baran.database
//All done by Ebrahim
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room.databaseBuilder

@Database(entities = [CardioEntity::class, WeightEntity::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun cardioDao(): CardioDao
    abstract fun weightDao(): WeightDao

    companion object {
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getInstance(context: Context): ApplicationDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    ApplicationDatabase::class.java,
                    "exerciseDB.db"
                )
                    .build()
            }
            return INSTANCE as ApplicationDatabase
        }
    }
}