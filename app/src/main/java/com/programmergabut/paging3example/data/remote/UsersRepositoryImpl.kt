package com.programmergabut.paging3example.data.remote

import com.programmergabut.paging3example.util.Result
import com.programmergabut.paging3example.base.BaseService
import com.programmergabut.paging3example.data.remote.json.UsersResponse
import com.programmergabut.paging3example.data.remote.service.GithubUsersService
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val githubUsersService: GithubUsersService): BaseService(), UsersRepository {

    override suspend fun fetchUsers(query: String, page: String, per_page: String): UsersResponse {
        return when(val result = createCall { githubUsersService.fetchGithubUser(query, page, per_page) }){
            is Result.Success -> result.data
            is Result.Error -> throw result.exception
        }
    }
}