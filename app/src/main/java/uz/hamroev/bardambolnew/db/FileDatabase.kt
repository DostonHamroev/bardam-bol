package uz.hamroev.bardambolnew.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FileEntity::class], version = 1)

abstract class FileDatabase : RoomDatabase() {

    abstract fun fileDao(): FileDao

    companion object {
        private var instance: FileDatabase? = null

        @Synchronized
        fun getInstance(context: Context): FileDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    FileDatabase::class.java,
                    "filepath.db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}