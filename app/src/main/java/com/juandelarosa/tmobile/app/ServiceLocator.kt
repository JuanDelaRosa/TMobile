package com.juandelarosa.tmobile.app

import com.juandelarosa.data.api.NetworkModule
import com.juandelarosa.data.mappers.TMobileMapper
import com.juandelarosa.data.repositories.TMobileRemoteDataSourceImpl
import com.juandelarosa.data.repositories.TMobileRepositoryImpl
import com.juandelarosa.domain.repositories.TMobileRepository

object ServiceLocator {
    private val networkModule by lazy {
        NetworkModule()
    }
    @Volatile
    var repository: TMobileRepository? = null

    fun provideRepository() : TMobileRepository{
        synchronized(this){
            return repository ?: createRepository()
        }
    }

    private fun createRepository(): TMobileRepository {
        val newRepo =
            TMobileRepositoryImpl(
                TMobileRemoteDataSourceImpl(
                    networkModule.createTMobileAPI("https://private-8ce77c-tmobiletest.apiary-mock.com/"),
                    TMobileMapper()
                )
            )
        repository = newRepo
        return newRepo
    }
}