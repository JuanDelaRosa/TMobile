package com.juandelarosa.tmobile.app

import android.content.Context
import com.juandelarosa.data.api.NetworkModule
import com.juandelarosa.data.db.TMDataBase
import com.juandelarosa.data.mappers.TMobileDBMapper
import com.juandelarosa.data.mappers.TMobileMapper
import com.juandelarosa.data.repositories.TMobileLocalDataSourceImpl
import com.juandelarosa.data.repositories.TMobileRemoteDataSourceImpl
import com.juandelarosa.data.repositories.TMobileRepositoryImpl
import com.juandelarosa.domain.repositories.TMobileRepository

object ServiceLocator {
    private val networkModule by lazy {
        NetworkModule()
    }
    @Volatile
    var repository: TMobileRepository? = null

    private var database: TMDataBase? = null
    private val localMapper by lazy { TMobileDBMapper() }

    fun provideRepository(context: Context) : TMobileRepository{
        synchronized(this){
            return repository ?: createRepository(context)
        }
    }

    private fun createRepository(context: Context): TMobileRepository {
        val newRepo =
            TMobileRepositoryImpl(
                TMobileRemoteDataSourceImpl(
                    networkModule.createTMobileAPI("https://private-8ce77c-tmobiletest.apiary-mock.com/"),
                    TMobileMapper()
                ),
                createTMLocalDataSource(context)
            )
        repository = newRepo
        return newRepo
    }

    private fun createTMLocalDataSource(context: Context) : TMobileLocalDataSourceImpl {
        val database = database ?: createDataBase(context)
        return TMobileLocalDataSourceImpl(
            database.dao(),
            localMapper
        )
    }

    private fun createDataBase(context: Context): TMDataBase {
        val result = TMDataBase.getDataBase(context)
        database = result
        return result
    }
}