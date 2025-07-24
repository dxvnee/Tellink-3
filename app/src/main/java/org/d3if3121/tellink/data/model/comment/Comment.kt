package org.d3if3121.tellink.data.model.comment

import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa

data class Comment(
    val nim: String,
    val id: String,
    val projectId: String,
    val commentId: String?,
    val comment: String,
    val replyCount: Int,
    val date: String,
    val mahasiswa: Mahasiswa,
)
