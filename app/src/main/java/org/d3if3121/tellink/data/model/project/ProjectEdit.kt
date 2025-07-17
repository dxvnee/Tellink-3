package org.d3if3121.tellink.data.model.project


data class ProjectEdit (
    val nim: String,
    val title: String,
    val desc: String,
    val image: String?,
    val tag: List<String>,
)