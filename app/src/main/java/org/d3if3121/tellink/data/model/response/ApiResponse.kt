package org.d3if3121.tellink.data.model.response

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T?
)
