package com.example.domain.sources.user

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.UserDomain

interface GetUsersRemoteSource {
    suspend operator fun invoke(): LocalResult<List<UserDomain>, DataError>
}
