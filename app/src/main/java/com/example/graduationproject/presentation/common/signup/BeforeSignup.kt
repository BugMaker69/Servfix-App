package com.example.graduationproject.presentation.common.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.R
import com.example.graduationproject.ui.theme.DarkBlue


@Composable
fun BeforeSignup(
    modifier: Modifier = Modifier,
    onBecomeClick: () -> Unit,
    onHireClick: () -> Unit,
    onLoginClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .verticalScroll(rememberScrollState()),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box(modifier = Modifier.padding(top = 16.dp), contentAlignment = Alignment.TopCenter) {

            Image(
                painter = painterResource(id = R.drawable.servix_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp),
                alignment = Alignment.TopCenter,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButtonAndText(
                    text = R.string.become_service_provider,
                    onClick = onBecomeClick,
                    contentColor = DarkBlue,
                    style = TextStyle(letterSpacing = .9.sp),
                    fontSize = 32
                )
            }

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxHeight(.9f)
                    .padding(vertical = 32.dp)
                    .width(2.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButtonAndText(
                    text = R.string.hire_service_provider,
                    onClick = onHireClick,
                    contentColor = DarkBlue,
                    style = TextStyle(letterSpacing = .9.sp),
                    fontSize = 32
                )
            }
        }


        Divider(thickness = 3.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
            CustomButtonAndText(text = R.string.have_account)
            CustomButtonAndText(
                text = R.string.login,
                onClick = onLoginClick,
                contentColor = Color.Blue
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun BeforeSignupPreview() {
    BeforeSignup(Modifier, {}, {}, {})
}
