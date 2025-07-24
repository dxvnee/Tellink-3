package org.d3if3121.tellink.data.model.comment

data class CommentRequest(
    val nim: String,
    val projectId: String,
    val comment: String,
    val commentId: String?,
)
