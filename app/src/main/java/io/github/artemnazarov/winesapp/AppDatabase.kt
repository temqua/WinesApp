package io.github.artemnazarov.winesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.artemnazarov.winesapp.data.Wine
import io.github.artemnazarov.winesapp.data.WineDao

@Database(entities = [Wine::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wineDao(): WineDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }


        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "winedb").build()
        }


//        fun getDatabase(context: Context): AppDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "wine_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
    }
}