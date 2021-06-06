package com.juandelarosa.domain.repositories

import com.juandelarosa.domain.common.Result
import com.juandelarosa.domain.entities.Cards
import com.juandelarosa.domain.entities.CardsDB

//Dedicated interface to communicate all available repositories
interface TMobileRepository {
    suspend fun LoadHomeFeeds() : Result<List<Cards>>

    suspend fun GetBackup() : Result<CardsDB>

    suspend fun SaveBackup(backup :CardsDB)
}