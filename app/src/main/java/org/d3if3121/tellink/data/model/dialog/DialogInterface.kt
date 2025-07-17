package org.d3if3121.tellink.data.model.dialog


sealed class DialogInterface(
    val successTitle: String,
    val failureTitle: String,
    val successMessage: String,
    val failureMessage: String,
){
    object Add: DialogInterface(
        successTitle  = "Post Success",
        failureTitle  = "Post Failed",
        successMessage = "Post has uploaded!",
        failureMessage = "Internal Server Error."
    )
    object Edit: DialogInterface(
        successTitle  = "Edit Success",
        failureTitle  = "Edit Failed",
        successMessage = "Edit has uploaded!",
        failureMessage = "Internal Server Error."
    )
    object Delete: DialogInterface(
        successTitle  = "Delete Success",
        failureTitle  = "Delete Failed",
        successMessage = "Delete has uploaded!",
        failureMessage = "Internal Server Error."
    )
}
