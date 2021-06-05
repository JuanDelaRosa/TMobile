package com.juandelarosa.data.repositories

import com.juandelarosa.domain.common.Result
import com.juandelarosa.domain.entities.Cards

interface TMobileRemoteDataSource {
    suspend fun getHomeFeeds(): Result<List<Cards>>
}