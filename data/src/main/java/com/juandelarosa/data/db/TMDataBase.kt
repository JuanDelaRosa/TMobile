package com.juandelarosa.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juandelarosa.data.db.entities.LocalBackup


@Database(entities = [LocalBackup::class], version = 1, exportSchema = false)
abstract class TMDataBase : RoomDatabase() {
    abstract  fun dao() : DAO

    companion object{
        @Volatile
        private var _Instance : TMDataBase? = null

        fun getDataBase(appContext: Context): TMDataBase{
            val tmp = _Instance
            if(tmp != null){
                return tmp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    appContext,
                    TMDataBase::class.java,
                    TMDataBase::class.simpleName.toString()
                ).fallbackToDestructiveMigration()
                    .build()
                _Instance = instance
                return instance
            }
        }
    }
}