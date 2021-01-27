package com.programmergabut.paging3example.data.remote.json


import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)