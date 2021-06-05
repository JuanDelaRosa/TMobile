package com.juandelarosa.data.repositories

import com.juandelarosa.domain.common.Result
import com.juandelarosa.domain.entities.Cards
import com.juandelarosa.domain.repositories.TMobileRepository

class TMobileRepositoryImpl(
    private val remoteDataSource: TMobileRemoteDataSource
    ): TMobileRepository {
    override suspend fun LoadHomeFeeds(): Result<List<Cards>> =
        remoteDataSource.getHomeFeeds()

}