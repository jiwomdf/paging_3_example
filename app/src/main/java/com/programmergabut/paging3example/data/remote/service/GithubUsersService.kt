package com.programmergabut.paging3example.data.remote.service

import com.programmergabut.paging3example.data.remote.json.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubUsersService {
    /* https://api.github.com/search/users?q={query}{&page,per_page,sort,order} */
    @GET("search/users")
    suspend fun fetchGithubUser(
        @Query("q") query: String,
        @Query("page") page: String,
        @Query("per_page") per_page: String
    ): Response<UsersResponse>
}