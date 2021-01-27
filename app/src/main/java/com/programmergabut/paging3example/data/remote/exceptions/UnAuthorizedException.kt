package com.programmergabut.paging3example.data.remote.exceptions

import java.io.IOException

const val USER_AUTHORIZED = "User Unauthorized"
class UnAuthorizedException: IOException() {
    override val message: String
        get() = USER_AUTHORIZED
}