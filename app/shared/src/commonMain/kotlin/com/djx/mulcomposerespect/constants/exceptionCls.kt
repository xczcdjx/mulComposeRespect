package com.djx.mulcomposerespect.constants

import kotlinx.io.IOException

class ApiException(
    val code: Int,
    override val message: String
) : IOException(message)