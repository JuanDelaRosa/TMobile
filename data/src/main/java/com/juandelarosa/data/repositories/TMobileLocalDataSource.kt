package com.juandelarosa.data.repositories

import com.juandelarosa.domain.common.Result
import com.juandelarosa.domain.entities.CardsDB

interface TMobileLocalDataSource {
    suspend fun saveBackup(backup: CardsDB)
    suspend fun getBackup() : Result<CardsDB>
}