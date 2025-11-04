package com.example.domain.usecase.user

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.UserDomain

interface GetUsersUseCase {
    suspend operator fun invoke(): LocalResult<List<UserDomain>, DataError>
}
