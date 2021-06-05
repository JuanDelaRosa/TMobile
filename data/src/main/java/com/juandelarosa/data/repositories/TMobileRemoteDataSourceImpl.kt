package com.juandelarosa.data.repositories

import com.juandelarosa.data.api.TMobileService
import com.juandelarosa.data.mappers.TMobileMapper
import com.juandelarosa.domain.common.Result
import com.juandelarosa.domain.entities.Cards
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class TMobileRemoteDataSourceImpl (private val service: TMobileService, private val mapper: TMobileMapper) : TMobileRemoteDataSource {
    override suspend fun getHomeFeeds(): Result<List<Cards>> =
        withContext(Dispatchers.IO){
            try{
                val response = service.HomePageFeeds()
                if(response.isSuccessful)
                    return@withContext Result.Success(mapper.toFeeds(response.body()))
                else
                    return@withContext Result.Error(Exception(response.message()))
            }catch (e:Exception){
                return@withContext Result.Error(e)
            }
        }
}