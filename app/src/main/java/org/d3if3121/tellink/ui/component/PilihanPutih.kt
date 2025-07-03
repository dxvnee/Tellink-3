package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.ui.animation.AnimationPairButtonColor1
import org.d3if3121.tellink.ui.animation.AnimationPairButtonColor2
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun PilihanPutih(
    text1: String = "Text1",
    text2: String = "Text2",
    condition: Boolean = false,
    onclick1: () -> Unit = {},
    onclick2: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp),
        colors = CardDefaults.cardColors(containerColor = Warna.PutihNormal),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row {
            val (backgroundColor1, fontWeight1) = AnimationPairButtonColor1(condition)
            val (backgroundColor2, fontWeight2) = AnimationPairButtonColor2(condition)

            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor1,
                    contentColor = if (condition) Color.White else Color.Black
                ),
                onClick = onclick1
            ) {
                Text(
                    text = text1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(fontWeight1.toInt())
                )
            }

            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor2,
                    contentColor = if (!condition) Color.White else Color.Black
                ),
                onClick = onclick2
            ) {
                Text(
                    text = text2,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(fontWeight2.toInt())
                )
            }
        }
    }
}
