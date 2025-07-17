package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.R
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.ui.formula.formatRelativeTime
import org.d3if3121.tellink.ui.screen.content.component.ButtonMerahBehaviour
import org.d3if3121.tellink.ui.screen.content.component.ButtonMerahKartuType
import org.d3if3121.tellink.ui.screen.content.component.GambarHandler

@Composable
fun KartuKonten(
    mahasiswa: Mahasiswa,
    project: Project,
    viewModel: GambarHandler,
    buttonbehaviour: ButtonMerahBehaviour,
    onClick: () -> Unit = {}
){
    CardPutihBesar(modifier = Modifier.clickable { onClick() }) {
        Row {
            Gambar(painterResource(id = R.drawable.photo), 53.dp, Modifier.padding(end = 10.dp))

            Column {
                TeksBoldMerah(mahasiswa.nama, Modifier.offset(y = (-2).dp))
                TeksBoldAbu(mahasiswa.jurusan, Modifier.offset(y = (-7).dp))
                TeksNormalAbu(formatRelativeTime(project.date), Modifier.offset(y = (-13).dp))
            }
        }

        TeksBold(project.title)

        DisplayTag(project.tag, Modifier.padding(top = 5.dp))

        Space(15)

        AsyncGambar(project.image, viewModel)

        Space(12)

        TeksNormal(project.desc, textAlign =  TextAlign.Justify)

        Space(35)

        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center){
            ButtonMerahKartuType(buttonbehaviour)
        }
    }
}

