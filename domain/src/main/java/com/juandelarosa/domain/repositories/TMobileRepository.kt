package com.juandelarosa.domain.repositories

import com.juandelarosa.domain.common.Result
import com.juandelarosa.domain.entities.Cards

interface TMobileRepository {
    suspend fun LoadHomeFeeds() : Result<List<Cards>>
   // suspend fun LoadHomeFeedsFromDB(): Result<List<Cards>>
}