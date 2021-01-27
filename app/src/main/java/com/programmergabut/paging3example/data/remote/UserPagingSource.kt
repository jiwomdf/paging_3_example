package com.programmergabut.paging3example.data.remote

import androidx.paging.PagingSource
import com.programmergabut.paging3example.data.remote.json.Item
import com.programmergabut.paging3example.util.Constant.Companion.GITHUB_API_PER_PAGE
import com.programmergabut.paging3example.util.Constant.Companion.GITHUB_API_STARTING_PAGE
import java.lang.NullPointerException

const val RESPONSE_ITEMS_NULL_MSG = "response items is null"
const val RESPONSE_TOTAL_COUNT_NULL_MSG = "response total count is null"
class UserPagingSource(
    private val repository: UsersRepository,
    private val query: String
): PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val startingPage = GITHUB_API_STARTING_PAGE
        val position = params.key ?: startingPage
        val perPage = GITHUB_API_PER_PAGE

        return try {
            val response = repository.fetchUsers(query, position.toString(), perPage.toString())
            if(response.items != null /* && response.totalCount != null */){
                LoadResult.Page(
                    data = response.items,
                    prevKey = if(position == startingPage) null else position - 1,
                    nextKey = if( /* position >= response.totalPages || */ response.items.isEmpty()) null else position + 1
                )
            }
            /*else if(response.totalCount == null) {
                LoadResult.Error(NullPointerException(RESPONSE_TOTAL_COUNT_NULL_MSG))
            } */
            else {
                LoadResult.Error(NullPointerException(RESPONSE_ITEMS_NULL_MSG))
            }
        } catch (ex: Exception){
            LoadResult.Error(ex)
        }
    }
}