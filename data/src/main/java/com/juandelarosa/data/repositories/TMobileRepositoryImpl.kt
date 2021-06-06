package com.juandelarosa.data.repositories

import com.juandelarosa.domain.common.Result
import com.juandelarosa.domain.entities.Cards
import com.juandelarosa.domain.entities.CardsDB
import com.juandelarosa.domain.repositories.TMobileRepository

//Main repository, dedicated to communicating with the other repositories.
class TMobileRepositoryImpl(
    private val remoteDataSource: TMobileRemoteDataSource,
    private val localDataSource: TMobileLocalDataSource
    ): TMobileRepository {
    override suspend fun LoadHomeFeeds(): Result<List<Cards>> =
        remoteDataSource.getHomeFeeds()

    override suspend fun GetBackup(): Result<CardsDB> =
        localDataSource.getBackup()


    override suspend fun SaveBackup(backup: CardsDB) {
        localDataSource.saveBackup(backup)
    }

}