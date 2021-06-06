package com.juandelarosa.domain.usercases

import com.juandelarosa.domain.repositories.TMobileRepository

//User case to consume the information from the api service
class GetHomeFeedsUserCase(private val repository: TMobileRepository) {
    suspend operator fun invoke() = repository.LoadHomeFeeds()
}