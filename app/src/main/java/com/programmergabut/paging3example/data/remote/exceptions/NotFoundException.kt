package com.programmergabut.paging3example.data.remote.exceptions

import java.io.IOException

const val NOT_FOUND = "Not Found"
class NotFoundException: IOException() {
    override val message: String
        get() = NOT_FOUND
}