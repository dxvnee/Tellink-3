package org.d3if3121.tellink.data.model.dialog

class DialogStateInterface(
    val successTitle: String,
    val failureTitle: String,
    val successMessage: String,
    val failureMessage: String,
){
    companion object {
        val ADD = DialogStateInterface(
            successTitle = "Post Success",
            failureTitle = "Post Failed",
            successMessage = "Post has been uploaded!",
            failureMessage = "Internal Server Error."
        )

        val EDIT = DialogStateInterface(
            successTitle = "Edit Success",
            failureTitle = "Edit Failed",
            successMessage = "Changes have been saved!",
            failureMessage = "Edit operation failed."
        )

        val DELETE = DialogStateInterface(
            successTitle = "Delete Success",
            failureTitle = "Delete Failed",
            successMessage = "Post has been deleted!",
            failureMessage = "Unable to delete post."
        )
    }
}

class DialogConfirmInterface(
    val title: String,
    val message: String,
    val buttontext: String,
    val dismissText: String = "",
){
    companion object {
        val DELETE = DialogConfirmInterface(
            title = "Delete Post",
            message = "Are you sure?",
            buttontext = "YES",
            dismissText = "NO"
        )

        val EDIT = DialogConfirmInterface(
            title = "Edit Post",
            message = "Are you sure?",
            buttontext = "YES",
            dismissText = "NO"
        )
    }
}




