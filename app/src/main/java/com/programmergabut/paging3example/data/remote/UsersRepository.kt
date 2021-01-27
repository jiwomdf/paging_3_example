package com.programmergabut.paging3example.data.remote

import com.programmergabut.paging3example.data.remote.json.UsersResponse

interface UsersRepository {
    suspend fun fetchUsers(query: String, page: String, per_page: String): UsersResponse
}