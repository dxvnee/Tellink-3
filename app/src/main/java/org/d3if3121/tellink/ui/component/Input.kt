package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.d3if3121.tellink.R
import org.d3if3121.tellink.navigation.component.Screen
import org.d3if3121.tellink.ui.theme.Warna
import kotlin.math.exp

@Composable
fun InputPutih(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    expand: Boolean = false,
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Warna.AbuTua,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(
                max = if (expand) Int.MAX_VALUE.dp else 50.dp,
                min = if (expand) 180.dp else 50.dp
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Warna.HitamNormal,
            unfocusedTextColor = Warna.HitamNormal,
            focusedPlaceholderColor = Warna.MerahNormal,
            focusedIndicatorColor = Warna.MerahNormal,
            unfocusedIndicatorColor = Warna.PutihGelap,
            focusedContainerColor = if (isFocused) Warna.PutihNormal else Warna.PutihGelap,
            unfocusedContainerColor = if (isFocused) Warna.PutihNormal else Warna.PutihGelap
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        maxLines = if (expand) Int.MAX_VALUE else 1,
        minLines = 1,
    )
}


@Composable
fun InputPutihSearchProfile(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    fontSize: Int = 17,
) {
    var isFocused by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .border(
                1.dp,
                if (!isFocused) Warna.AbuMuda else Warna.MerahNormal,
                RoundedCornerShape(10.dp)
            )
            .fillMaxHeight(),
        colors = if (!isFocused) CardDefaults.cardColors(Warna.AbuMuda) else CardDefaults.cardColors(
            Warna.PutihNormal
        )

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 10.dp)
        ) {
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "eheh",
                tint = Warna.AbuTua
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 7.dp)
            ) {
                if (input.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Warna.AbuTua,
                        fontSize = fontSize.sp,
                        style = TextStyle(
                            fontSize = fontSize.sp,
                            color = Warna.HitamNormal,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.offset(y = 8.dp)

                    )
                }
                BasicTextField(
                    value = input,
                    onValueChange = onInputChange,
                    textStyle = TextStyle(
                        fontSize = fontSize.sp,
                        color = Warna.HitamNormal,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = if (input.isEmpty()) -9.dp else 0.dp)
                        .onFocusChanged {
                            isFocused = it.isFocused
                        },
                )
            }
        }


    }
}

@Composable
fun InputPutihSearch(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    fontSize: Int = 15,
) {
    var isFocused by remember { mutableStateOf(false) }
    OutlinedTextField(
        leadingIcon = {
            Icon(
                modifier = iconModifier.size(25.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "eheh",
                tint = Warna.AbuTua,

                )
        },
        textStyle = TextStyle(
            color = Warna.HitamNormal,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Normal,
        ),
        value = input,
        onValueChange = onInputChange,
        singleLine = true,
        placeholder = {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = placeholder,
                    color = Warna.AbuTua,
                    fontSize = fontSize.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }

        },
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .height(47.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Warna.HitamNormal,
            unfocusedTextColor = Warna.HitamNormal,
            focusedPlaceholderColor = Warna.MerahNormal,
            focusedIndicatorColor = Warna.MerahNormal,
            unfocusedIndicatorColor = Warna.PutihGelap,
            focusedContainerColor = if (isFocused) Warna.PutihNormal else Warna.PutihGelap,
            unfocusedContainerColor = if (isFocused) Warna.PutihNormal else Warna.PutihGelap
        ),


        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),

        )
}


@Composable
fun SearchBarWithButton(
    search: String,
    onSearchChange: (String) -> Unit,
    navControllerGlobal: NavHostController,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 17.dp)
    ) {
        InputPutihSearchNative(
            input = search,
            placeholder = "Search...",
            onInputChange = { onSearchChange(it) },
            keyboardType = KeyboardType.Text,
            fontSize = 15,
            modifier = Modifier
                .weight(8f)
                .padding(end = 10.dp)
        )

        TombolTambah(modifier = Modifier.weight(1f)) {
            navControllerGlobal.navigate(Screen.ProjectAdd.route)
        }
    }
}

@Composable
fun InputPutihSearchNative(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    fontSize: Int = 15,
    isFocusedColor: Color = Warna.PutihNormal,
    unfocusedColor: Color = Warna.PutihGelap,
    height: Dp = 40.dp,
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .height(height)
            .background(if (isFocused) isFocusedColor else unfocusedColor)
            .border(
                width = 2.dp,
                color = if (isFocused) Warna.MerahNormal else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .onFocusChanged { isFocused = it.isFocused }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon",
                modifier = iconModifier.size(25.dp),
                tint = if (isFocused) Warna.HitamNormal else Warna.AbuTua
            )

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = input,
                onValueChange = onInputChange,
                singleLine = true,
                textStyle = TextStyle(
                    color = Warna.HitamNormal,
                    fontSize = fontSize.sp,
                    fontWeight = FontWeight.Normal
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                decorationBox = { innerTextField ->
                    if (input.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Warna.AbuTua,
                            fontSize = fontSize.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}


@Composable
fun InputPutihNative(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    expand: Boolean = false,
) {
    var isFocused by remember { mutableStateOf(false) }

    val backgroundColor = if (isFocused) Warna.PutihNormal else Warna.PutihGelap
    val borderColor = if (isFocused) Warna.MerahNormal else Warna.PutihGelap

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = if (expand) 180.dp else 50.dp, max = if (expand) Dp.Infinity else 41.dp)
            .border(width = 2.dp, color = borderColor, shape = RoundedCornerShape(10.dp))
            .background(color = backgroundColor, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 16.dp)
    ) {
        RowStartCenter(modifier = Modifier.fillMaxSize()){
            BasicTextField(
                value = input,
                onValueChange = onInputChange,
                decorationBox = { if (input.isEmpty()) {
                    Text(text = placeholder, modifier = Modifier.padding(top = if(expand) 8.dp else 0.dp), color = Warna.AbuTua, fontSize = 15.sp, fontWeight = FontWeight.Normal) }
                    Box(modifier = Modifier.padding(top = if(expand) 10.dp else 0.dp)){ it() }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                textStyle = TextStyle(color = Warna.HitamNormal, fontSize = 15.sp,),
                modifier = Modifier.fillMaxWidth().onFocusChanged { isFocused = it.isFocused },
                maxLines = if (expand) Int.MAX_VALUE else 1,
                singleLine = !expand
            )
        }
    }
}


@Composable
fun InputPasswordNative(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    passwordVisible: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    iconWeight: Float = 2f
) {
    var isFocused by remember { mutableStateOf(false) }

    val backgroundColor = if (isFocused) Warna.PutihNormal else Warna.PutihGelap
    val borderColor = if (isFocused) Warna.MerahNormal else Warna.PutihGelap
    val shape = RoundedCornerShape(10.dp)

    val image =
        if (passwordVisible.value) painterResource(id = R.drawable.baseline_visibility_24)
        else painterResource(id = R.drawable.baseline_visibility_off_24)

    Box(
        modifier = modifier.height(40.dp).clip(shape).background(color = backgroundColor, shape = shape)
            .fillMaxSize()

            .border(width = 2.dp, color = borderColor, shape = shape)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
    ){
        Row (modifier = Modifier.fillMaxSize().padding(start = 17.dp), verticalAlignment = Alignment.CenterVertically){
            BasicTextField(
                value = input,
                onValueChange = onInputChange,
                decorationBox = {
                    if (input.isEmpty()) {
                        Text(text = placeholder, color = Warna.AbuTua, fontSize = 15.sp, fontWeight = FontWeight.Normal, maxLines = 1)
                    }
                    it()
                },
                modifier = Modifier.weight(iconWeight),
                textStyle = TextStyle(color = Warna.HitamNormal, fontSize = 15.sp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
            )


            Box(modifier = Modifier.weight(1f)){
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(
                        painter = image,
                        contentDescription = if (passwordVisible.value) "Hide password" else "Show password",
                        tint = Warna.MerahNormal
                    )
                }
            }
        }
    }

}

@Composable
fun InputPassword(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    passwordVisible: MutableState<Boolean>,
    modifiers: Modifier = Modifier,
) {
    var isFocused by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        placeholder = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = placeholder,
                    color = Warna.AbuTua,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }

        },

        shape = RoundedCornerShape(10.dp),
        modifier = modifiers
            .height(50.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Warna.HitamNormal,
            unfocusedTextColor = Warna.HitamNormal,
            focusedPlaceholderColor = Warna.MerahNormal,
            focusedIndicatorColor = Warna.MerahNormal,
            unfocusedIndicatorColor = Warna.PutihGelap,
            focusedContainerColor = if (isFocused) Warna.PutihNormal else Warna.PutihGelap,
            unfocusedContainerColor = if (isFocused) Warna.PutihNormal else Warna.PutihGelap
        ),

        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image =
                if (passwordVisible.value) painterResource(id = R.drawable.baseline_visibility_24)
                else painterResource(id = R.drawable.baseline_visibility_off_24)

            IconButton(onClick = {
                passwordVisible.value = !passwordVisible.value
            }) {
                Icon(
                    painter = image,
                    contentDescription = if (passwordVisible.value) "Hide password" else "Show password",
                    tint = Warna.MerahNormal
                )
            }
        }

    )
}


