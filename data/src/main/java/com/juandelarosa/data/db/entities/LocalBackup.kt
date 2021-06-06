package com.juandelarosa.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//Information that is saved in the database as a backup in case of not having internet
@Entity(tableName = "backup")
data class LocalBackup(
    @PrimaryKey()
    val id : Int = 0,
    val info: String
)
