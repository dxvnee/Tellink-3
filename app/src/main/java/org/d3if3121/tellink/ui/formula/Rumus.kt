package org.d3if3121.tellink.ui.formula

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId.*
import java.time.format.DateTimeFormatter


fun formatRelativeTime(dateTimeString: String): String {
    if (dateTimeString == "") {
        return "unknown time"
    } else {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val parsedDateTime = LocalDateTime.parse(dateTimeString, formatter)

        val now = LocalDateTime.now(systemDefault())
        val duration = Duration.between(parsedDateTime, now)

        return when {
            duration.toMinutes() < 1 -> "just now"
            duration.toHours() < 1 -> "${duration.toMinutes()} minutes ago"
            duration.toDays() < 1 -> "${duration.toHours()} hours ago"
            duration.toDays() < 7 -> "${duration.toDays()} days ago"
            duration.toDays() < 30 -> "${duration.toDays() / 7} weeks ago"
            duration.toDays() < 365 -> "${duration.toDays() / 30} months ago"
            else -> "${duration.toDays() / 365} years ago"
        }
    }
}


fun uriToFile(context: Context, uri: Uri): File {
    val inputStream = context.contentResolver.openInputStream(uri)

    val tempFile = File.createTempFile("upload_", ".jpg", context.cacheDir)
    tempFile.outputStream().use { outputStream ->
        inputStream?.copyTo(outputStream)
    }

    return tempFile
}

fun <T> toRequestBody(data: T): RequestBody {
    val gson = Gson()
    val json = gson.toJson(data)
    return json.toRequestBody("text/plain".toMediaTypeOrNull())
}

fun toMultipartBody(file: File, context: Context, imageUri: Uri): MultipartBody.Part {
    val mimeType = getMimeType(file, context, imageUri)
    Log.d("wow", mimeType)
    val reqFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("image", file.name, reqFile)
}

fun getMimeType(file: File, context: Context, uri: Uri): String {
    val ext = file.extension.lowercase()
    val extMime = when (ext) {
        "jpg", "jpeg" -> "image/jpeg"
        "png" -> "image/png"
        "gif" -> "image/gif"
        "bmp" -> "image/bmp"
        "webp" -> "image/webp"
        else -> null
    }
    if (extMime != null) return extMime

    val contentMime = context.contentResolver.getType(uri)
    if (!contentMime.isNullOrEmpty() && contentMime != "application/octet-stream") {
        return contentMime
    }

    return "image/*"
}
