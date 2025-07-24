package org.d3if3121.tellink.ui.screen.content.projectpage.component

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.R
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.ui.component.AddProjectImage
import org.d3if3121.tellink.ui.component.ButtonMerah
import org.d3if3121.tellink.ui.component.DisplayTag
import org.d3if3121.tellink.ui.component.DropdownTag
import org.d3if3121.tellink.ui.component.InputPutihNative
import org.d3if3121.tellink.ui.component.RowEnd
import org.d3if3121.tellink.ui.component.Space
import org.d3if3121.tellink.ui.component.SpaceWidth
import org.d3if3121.tellink.ui.component.TeksBoldMerah
import org.d3if3121.tellink.ui.component.TeksBoldTombol


@Composable
fun ProjectForm(
    isEdit: Boolean,
    currentProject: Project? = null,
    imageDataString: String = "",
    onSubmit: (
        id: String,
        title: String,
        desc: String,
        tags: List<String>,
        imageData: String,
        imageUri: Uri?,
        context: Context
    ) -> Unit,
    onDelete: ((id: String) -> Unit)? = null
) {
    val context = LocalContext.current

    var id by remember { mutableStateOf(currentProject?.id ?: "") }
    var judul by remember { mutableStateOf(currentProject?.title ?: "") }
    var desc by remember { mutableStateOf(currentProject?.desc ?: "") }
    var tag by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageData by remember { mutableStateOf(imageDataString.ifEmpty { currentProject?.image ?: "" }) }
    var selectedTag by remember { mutableStateOf(currentProject?.tag ?: listOf()) }

    AddProjectImage(
        imageUri = imageUri,
        gambarString = imageData
    ) { imageUri = it }
    Space(20)

    TeksBoldMerah("Title: ", Modifier.padding(bottom = 5.dp))
    InputPutihNative(
        input = judul,
        placeholder = stringResource(id = R.string.edit_title),
        onInputChange = { judul = it },
        keyboardType = KeyboardType.Text
    )
    Space(20)

    TeksBoldMerah("Description: ", Modifier.padding(bottom = 5.dp))
    InputPutihNative(
        input = desc,
        expand = true,
        placeholder = stringResource(id = R.string.edit_title),
        onInputChange = { desc = it },
        keyboardType = KeyboardType.Text
    )
    Space(20)

    TeksBoldMerah("Tags: ", Modifier)
    if (selectedTag.isNotEmpty()) Space(5)

    DisplayTag(
        selectedTag = selectedTag,
        modifier = Modifier.padding(bottom = 10.dp),
        onTagRemove = { t -> selectedTag = selectedTag - t }
    )

    DropdownTag(
        selectedTag = tag,
        onTagSelected = { t ->
            if (selectedTag.size < 3) selectedTag = selectedTag + t
        }
    )
    Space(20)

    RowEnd(Modifier.fillMaxWidth()) {
        if (isEdit && onDelete != null) {
            ButtonMerah(
                onClick = { onDelete(id) },
                content = { TeksBoldTombol("Delete") }
            )
            SpaceWidth(10)
        }

        ButtonMerah(
            onClick = { onSubmit(id, judul, desc, selectedTag, imageData, imageUri, context) },
            content = { TeksBoldTombol(if (isEdit) "Edit" else "Post") }
        )
    }
}

