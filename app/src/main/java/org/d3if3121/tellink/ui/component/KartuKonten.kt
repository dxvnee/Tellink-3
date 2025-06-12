package org.d3if3121.tellink.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.R
import org.d3if3121.tellink.data.model.Project
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.ui.screen.content.component.ButtonMerahBehaviour
import org.d3if3121.tellink.ui.screen.content.component.ButtonMerahKartuType
import org.d3if3121.tellink.ui.screen.content.homepage.HomeViewModel
import org.d3if3121.tellink.ui.screen.content.projectpage.formatRelativeTime

@Composable
fun KartuKonten(
    mahasiswa: Mahasiswa,
    project: Project,
    homeViewModel: HomeViewModel,
    buttonbehaviour: ButtonMerahBehaviour,
){
    CardPutihBesar {
        Row {
            Gambar(painterResource(id = R.drawable.photo), 53.dp, Modifier.padding(end = 10.dp))

            Column {
                TeksBoldMerah(mahasiswa.nama, Modifier.offset(y = (-2).dp))
                TeksBoldAbu(mahasiswa.jurusan, Modifier.offset(y = (-7).dp))
                TeksNormalAbu(formatRelativeTime(project.date), Modifier.offset(y = (-13).dp))
            }
        }

        TeksBold(project.title, Modifier.padding(top = 0.dp, bottom = 5.dp))

        DisplayTag(project.tag)

        Space(15)

        AsyncGambar(project.image, homeViewModel)

        TeksNormal(project.desc, Modifier.padding(top = 12.dp, bottom = 12.dp), TextAlign.Justify)

        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center){
            ButtonMerahKartuType(buttonbehaviour)
        }
    }
}

