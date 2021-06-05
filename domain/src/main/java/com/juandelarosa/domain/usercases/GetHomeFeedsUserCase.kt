package com.juandelarosa.domain.usercases

import com.juandelarosa.domain.repositories.TMobileRepository

class GetHomeFeedsUserCase(private val repository: TMobileRepository) {
    suspend operator fun invoke() = repository.LoadHomeFeeds()
}