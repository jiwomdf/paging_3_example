package com.programmergabut.paging3example.data.remote.exceptions

import kotlin.Exception

const val SOME_UNKNOWN_ERROR_OCCURRED = "Some unknown error occurred"
class UnKnownException: Exception() {
    override val message: String?
        get() = SOME_UNKNOWN_ERROR_OCCURRED
}