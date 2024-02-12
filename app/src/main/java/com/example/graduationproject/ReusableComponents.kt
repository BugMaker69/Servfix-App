package com.example.graduationproject

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    fieldName: Int,
    fieldValue: String,
    onValueChange: (String) -> Unit,
    isError :Boolean =false,
    readOnly:Boolean = false,
    isValidField: (String) -> Boolean = { true },
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable() (() -> Unit)? = { },
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = fieldValue,
        onValueChange = onValueChange,
        isError = isError,
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
        readOnly = readOnly,
        singleLine = true,
        maxLines = 1,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon
    )
}


@Composable
fun CustomButtonAndText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: Int,
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
            style = style
        )
    }
}


@Composable
@Preview
fun CustomTextPreview() {
    CustomButtonAndText(onClick = {}, text = R.string.invalid)
}
