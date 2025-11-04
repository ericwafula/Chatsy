package com.example.domain.usecase.user

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.UserDomain
import com.example.domain.sources.user.GetUsersRemoteSource

class GetUsersUseCaseImpl(
    private val getUsersRemoteSource: GetUsersRemoteSource,
) : GetUsersUseCase {
    override suspend fun invoke(): LocalResult<List<UserDomain>, DataError> = getUsersRemoteSource()
}
