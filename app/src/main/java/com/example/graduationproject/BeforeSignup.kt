package com.example.graduationproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeforeSignup(
    modifier: Modifier = Modifier,
    onBecomeClick: () -> Unit,
    onHireClick: () -> Unit,
    onLoginClick: () -> Unit,
    userTypeViewModel: UserTypeViewModel
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

        Spacer(modifier = Modifier.height(64.dp))


        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Card(
                onClick = {
                    userTypeViewModel.userType.value = UserType.OwnerPerson
                    onHireClick()
                },
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.look_specialist),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .padding(16.dp),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.hire_service_provider),
                        modifier = Modifier
                            .fillMaxWidth(.7f)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .padding(16.dp)
                        ,
                        style = TextStyle(
                            letterSpacing = .9.sp,
                            fontSize = 24.sp,
                            color = Color.Black.copy(alpha = .6f),
                        ),
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_become),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )

                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                onClick = {
                    userTypeViewModel.userType.value = UserType.HirePerson
                    onBecomeClick()
                },
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(16.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.want_job),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .padding(16.dp),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.become_service_provider),
                        modifier = Modifier
                            .fillMaxWidth(.7f)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .padding(16.dp),
                        style = TextStyle(
                            letterSpacing = .9.sp,
                            fontSize = 24.sp,
                            color = Color.Black.copy(alpha = .6f),
                        ),
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_hire),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )

                }
            }
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
}


@Preview(showBackground = true)
@Composable
fun BeforeSignupPreview() {
    BeforeSignup(Modifier, {}, {}, {}, viewModel())
}
