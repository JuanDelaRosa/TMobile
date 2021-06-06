package com.juandelarosa.data.repositories

import com.juandelarosa.data.Exceptions
import com.juandelarosa.data.db.DAO
import com.juandelarosa.data.mappers.TMobileDBMapper
import com.juandelarosa.domain.common.Result
import com.juandelarosa.domain.entities.CardsDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TMobileLocalDataSourceImpl(
    private val dao: DAO,
    private val mapper : TMobileDBMapper
) : TMobileLocalDataSource {

    override suspend fun saveBackup(backup: CardsDB) {
        withContext(Dispatchers.IO){
            dao.saveBackup(mapper.toBackup(backup))
        }
    }

    override suspend fun getBackup(): Result<CardsDB> =
        withContext(Dispatchers.IO){
            val backup = dao.getBackup()
            if(backup==null)
                return@withContext Result.Error(Exception(Exceptions.NoBackup))
            else
                return@withContext Result.Success(mapper.fromBackup(backup))
        }
}