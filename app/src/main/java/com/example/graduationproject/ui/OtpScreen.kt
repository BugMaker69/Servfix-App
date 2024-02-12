package com.example.graduationproject.ui

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.graduationproject.CustomButtonAndText
import com.example.graduationproject.OtpViewModel
import com.example.graduationproject.R
import com.example.graduationproject.ui.theme.DarkBlue
import com.example.graduationproject.ui.theme.GraduationProjectTheme
import com.example.graduationproject.ui.theme.GrayBlue
import com.example.graduationproject.ui.theme.LightBlue
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OtpScreen(
     onLoginClick: () -> Unit
) {
    val otpViewModel: OtpViewModel = viewModel()
val context= LocalContext.current
    //do you think we shouldn't do that here ?
    otpViewModel.callbackmaker()
    val countdownTime by otpViewModel.countdownTime.collectAsState()
    val resendEnabled by otpViewModel.resendEnabled.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

        verticalArrangement = Arrangement.Top,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (otpViewModel.sucsess) {
            SucessAniamtion()
        }
        if (otpViewModel.mobileAnimation) {
            OtpAnimation()
        }
        if (otpViewModel.failed) {
            FailAniamtion()
        }


        CustomButtonAndText(
            text = R.string.Enter_Verification_Code, contentColor = Color.Black,
            fontSize = 30, fontWeight = FontWeight.ExtraBold
        )
        CustomButtonAndText(
            modifier = Modifier.padding(horizontal = 30.dp),
            text = R.string.Send_Otp_Info,
            contentColor = Color.Black,
            fontSize = 18,
            fontWeight = FontWeight.Normal
        )

        Text(
            text = otpViewModel.phoneNumber,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(alignment = Alignment.End)
        )

        OtpTextField(otpViewModel)
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomButtonAndText(
                contentColor = LightBlue,
                text = R.string.Resend_Code, fontWeight = FontWeight.Bold,
                onClick = {
                    otpViewModel.startCountdown()

                }
            )
            if (!resendEnabled) {
                Text(text = countdownTime.toString(), Modifier.padding(8.dp))
            }
        }

        Button(modifier = Modifier
            .size(width = 350.dp, height = 85.dp)
            .padding(20.dp),
            shape = RoundedCornerShape(36.dp),
            colors = ButtonDefaults.buttonColors(DarkBlue),
                        onClick = {
                otpViewModel.sendVerificationCode(
                    otpViewModel.phoneNumber,
                    otpViewModel.mAuth,
                    context as Activity,
                    callbacks = otpViewModel.callbacks
                )
                otpViewModel.startCountdown()
            }, enabled = resendEnabled
        ) {

            Text(text = stringResource(id = R.string.send),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                )
        }
        CustomButtonAndText(
            modifier = Modifier
                .size(width = 350.dp, height = 85.dp)
                .padding(20.dp),
            text = R.string.next,
            shape = RoundedCornerShape(36.dp),

            backgroundColor = DarkBlue,
            contentColor = Color.White,
            fontSize = 20,
            fontWeight = FontWeight.Normal,
            onClick = {
                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    otpViewModel.verificationID, otpViewModel.otpText
                )
                otpViewModel.signInWithPhoneAuthCredential(
                    credential,
                    otpViewModel.mAuth,
                    context as Activity,
                )
                CoroutineScope(Dispatchers.Main).launch {
                    delay(4000)
                    onLoginClick()
                }



            }
        )
    }


}

@Composable
fun FailAniamtion() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.failed))
    LottieAnimation(modifier = Modifier.size(170.dp), composition = composition, iterations = 1)
}

@Composable
fun SucessAniamtion() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.successotp))
    LottieAnimation(modifier = Modifier.size(170.dp), composition = composition, iterations = 1)
}

@Composable
fun OtpAnimation() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.otp))

    LottieAnimation(modifier = Modifier.size(170.dp), composition = composition)
}

@Composable
fun OtpTextField(otpViewModel: OtpViewModel) {

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), value = otpViewModel.otpText,
        onValueChange = {
            otpViewModel.updateOtpText(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions.Default
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(6) { index ->
                if (index > 0) {
                    Spacer(modifier = Modifier.width(6.dp))
                }
                val number = when {
                    index >= otpViewModel.otpText.length -> ""
                    else -> otpViewModel.otpText[index]
                }
                Column(
                    Modifier
                        .background(
                            if (number
                                    .toString()
                                    .isEmpty()
                            ) GrayBlue else DarkBlue,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp),
                        text = number.toString(),
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp

                    )

                }

            }
        }

    }
}


@Composable
@Preview
fun prevOtp() {
    GraduationProjectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                       OtpScreen(){

                       }

            }
        }
    }

}