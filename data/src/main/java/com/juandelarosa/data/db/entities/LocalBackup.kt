package com.juandelarosa.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "backup")
data class LocalBackup(
    @PrimaryKey()
    val id : Int = 0,
    val info: String
)
