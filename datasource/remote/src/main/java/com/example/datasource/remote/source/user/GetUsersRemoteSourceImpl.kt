package com.example.datasource.remote.source.user

import com.example.datasource.remote.helpers.ApiHelper
import com.example.datasource.remote.mappers.toDomain
import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.helpers.map
import com.example.domain.model.UserDomain
import com.example.domain.sources.user.GetUsersRemoteSource

class GetUsersRemoteSourceImpl(
    private val apiHelper: ApiHelper,
) : GetUsersRemoteSource {
    override suspend fun invoke(): LocalResult<List<UserDomain>, DataError> =
        apiHelper.getUsers().map { list ->
            list.map { it.toDomain() }
        }
}
