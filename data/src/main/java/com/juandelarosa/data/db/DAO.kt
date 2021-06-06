package com.juandelarosa.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juandelarosa.data.db.entities.LocalBackup

//Dedicated class to communicate with the database using Querys
@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBackup(backup : LocalBackup)

    @Query("SELECT * FROM backup where id = 0")
    suspend fun getBackup() : LocalBackup?
}