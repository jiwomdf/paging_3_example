package com.programmergabut.paging3example.data.remote.exceptions

import java.io.IOException

const val USER_FORBIDDEN = "User Forbidden"
class ForbiddenException : IOException() {
    override val message: String
        get() = USER_FORBIDDEN
}