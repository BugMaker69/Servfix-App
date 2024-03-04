@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.graduationproject.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.ui.BottomAppBar
import com.example.graduationproject.R
import com.example.graduationproject.ui.theme.DarkBlue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    fieldName: Int,
    fieldValue: String,
    onValueChange: (String) -> Unit,
    isError :Boolean =false,
    readOnly:Boolean = false,
//    isValidField: (String) -> Boolean = { true },
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable() (() -> Unit)? = { },
    leadingIcon: @Composable() (() -> Unit)? = null,
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = fieldValue,
        onValueChange = onValueChange,
        placeholder = { Text(stringResource(id = fieldName)) },
        label = { Text(stringResource(id = fieldName)) },
        keyboardOptions = keyboardOptions,

        supportingText = {
            if (
                isError
//                !isValidField(fieldValue)
            ) {
                Text(
                    text = stringResource(id = R.string.invalid) + " " + stringResource(id = fieldName),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        singleLine = true,
        maxLines = 1,
        readOnly = readOnly,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon
    )
}


@Composable
fun CustomButtonAndText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: Int,
    fontWeight: FontWeight=FontWeight.Normal,
    fontSize:Int=15,
    indication: Indication? = null,
    shape: Shape = RoundedCornerShape(4.dp),
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = Color.LightGray,
    style : TextStyle = TextStyle(),
    alignment: Alignment = Alignment.Center
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .background(backgroundColor, shape)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = indication
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = alignment
    ) {
        Text(
            text = stringResource(id = text),
            color = contentColor,
            style=style,
            fontSize = fontSize.sp,
          fontWeight = fontWeight
        )
    }
}



@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    title:String,
    navigationIcon : @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBlue, actionIconContentColor = Color.White, titleContentColor = Color.White,navigationIconContentColor = Color.White),
) {
    TopAppBar(
        title = { Text(text = title) },
        colors = colors,
        actions = actions,
        navigationIcon = navigationIcon,
    )

}




@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    confirmButtonColors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Red),
    dismissButtonColors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black),
    confirmButtonText : String,
    dismissButtonText : String,
    onConfirmButtonClick:()->Unit,
    onDismissButtonClick:()->Unit,
    dialogTitle:String,
    dialogText:String,
) {
    var show by remember { mutableStateOf(false) }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { show = false },
        confirmButton = {
            Button(onClick = onConfirmButtonClick,colors = confirmButtonColors) {
                Text(text = confirmButtonText,)
            }
        },
        dismissButton = {
            Button(onClick = onDismissButtonClick , colors = dismissButtonColors) {
                Text(text = dismissButtonText)
            }
        },
        title = {
            Text(
                text = dialogTitle,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = dialogText,
                textAlign = TextAlign.Center
            )
        }
    )
}


@Composable
fun ScreensTemplate(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = { BottomAppBar(Modifier, { "" }) },
    content: @Composable (PaddingValues) -> Unit
) {

    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar,
        content = content
    )

}




@Composable
@Preview
fun CustomTextPreview() {
    CustomButtonAndText(onClick = {}, text = R.string.invalid, fontSize = 10)
}
