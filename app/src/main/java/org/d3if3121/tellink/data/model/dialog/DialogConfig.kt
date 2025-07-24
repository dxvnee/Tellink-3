package org.d3if3121.tellink.data.model.dialog


data class DialogConfig (
    val title: String = "",
    val message: String = "",
    val confirmText: String = "",
    val onConfirm: () -> Unit = {},

    val dismissText: String = "",
    val onDismiss: () -> Unit = {},
)

