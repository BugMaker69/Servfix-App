package com.example.graduationproject

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.graduationproject.ui.theme.DarkBlue
import com.example.graduationproject.ui.theme.GrayBlue
import com.example.graduationproject.ui.theme.LightBlue


@Composable
fun ServicesHomePage(
    modifier: Modifier = Modifier,
    onTextFieldClick:()->Unit,
    onServiceItemClick:()->Unit,
) {

    Column() {

        ShareProblemBar(onTextFieldClick)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.background(Color.White)
//        columns = GridCells.Adaptive(70.dp)
        ) {

            items(10) { index ->
                ServicesItem(Modifier, R.drawable.ic_paint, R.string.login,onServiceItemClick)
            }
            item {
                ServicesItem(Modifier, R.drawable.ic_device, R.string.login,onServiceItemClick)
            }
        }
    }


}


@Composable
fun ServicesItem(
    modifier: Modifier = Modifier,
    @DrawableRes jobImage: Int,
    @StringRes jobText: Int,
    onServiceItemClick:()->Unit
) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onServiceItemClick() }
            .background(Color.White)
            .padding(16.dp)
            .border(width = 2.dp, color = LightBlue, shape = RoundedCornerShape(32.dp))
        ,
        shape = RoundedCornerShape(32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .background(Color.White)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                    painter = painterResource(id = jobImage),
                    contentDescription = stringResource(id = jobText),
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = LightBlue)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = jobText), color = Color.White)
            }
        }
    }
}


@Composable
fun ShareProblemBar(
    onTextFieldClick:()->Unit
) {

    var value by remember { mutableStateOf("")}

    CustomTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onTextFieldClick()
                Log.d("Clicked", "ShareProblemBar: Clicked")
            }
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    onTextFieldClick()
                    Log.d("Clicked", "ShareProblemBar: gained focus")
                }
            }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        fieldName = R.string.share_problem,
        fieldValue = value,
        onValueChange = {value=it},
        leadingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = "")}
    )

}








@Preview(showBackground = true)
@Composable
fun ServicesHomePagePreview() {
    ServicesHomePage(Modifier,{},{})
}


@Preview(showBackground = true)
@Composable
fun ShareProblemBarPreview() {
    ShareProblemBar({})
}


@Preview(showBackground = true)
@Composable
fun ServicesItemPreview() {
    ServicesItem(Modifier, R.drawable.ic_paint, R.string.login,{})
}