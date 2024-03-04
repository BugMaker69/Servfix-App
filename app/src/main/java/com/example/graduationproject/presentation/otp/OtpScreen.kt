package com.example.graduationproject.presentation.otp

import android.app.Activity

import android.util.Log
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.signup.UserViewModel
import com.example.graduationproject.ui.theme.DarkBlue
import com.example.graduationproject.ui.theme.GrayBlue
import com.example.graduationproject.ui.theme.LightBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OtpScreen(userViewModel: UserViewModel,
              onLoginClick: () -> Unit
) {
    val context = LocalContext.current
    val countdownTime by userViewModel.countdownTime.collectAsState()
    val resendEnabled by userViewModel.resendEnabled.collectAsState()
    Log.d(
        "yalla2",
        "OtpScreen: verificationid:${userViewModel.verificationID},otptext:${userViewModel.otpText},auth:${userViewModel.mAuth},phone:${userViewModel.phone}"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

        verticalArrangement = Arrangement.Top,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (userViewModel.sucsess) {
            SucessAniamtion()
        }
        if (userViewModel.mobileAnimation) {
            OtpAnimation()
        }
        if (userViewModel.failed) {
            FailAniamtion()
        }


        CustomButtonAndText(
            text = R.string.Enter_Verification_Code, contentColor = Color.Black,
            fontSize = 30, fontWeight = FontWeight.ExtraBold
        )


        Text(
            text = stringResource(R.string.Send_Otp_Info, userViewModel.phone),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(alignment = Alignment.End)
        )

        OtpTextField(userViewModel)
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomButtonAndText(
                contentColor = LightBlue,
                text = R.string.Resend_Code, fontWeight = FontWeight.Bold,
                onClick = {
                    userViewModel.startCountdown()
                    if(resendEnabled){

                        userViewModel.sendVerificationCode(activity=context as Activity, callbacks = userViewModel.callbacks
                        )
                    }

                }
            )
            if (!resendEnabled) {
                Text(text = countdownTime.toString(), Modifier.padding(8.dp))
            }
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
                if(userViewModel.verificationID!=""&&userViewModel.otpText!=""){
                    userViewModel.signInWithPhoneAuthCredential(
                        activity =  context as Activity,
                    )

                }
                if(userViewModel.sucsess){
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(3000)
                        onLoginClick()
                    }
                }
                else if (userViewModel.failed){
                    userViewModel.otpText=""
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
fun OtpTextField(userViewModel: UserViewModel) {

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), value = userViewModel.otpText,
        onValueChange = {
            userViewModel.updateOtpText(it)
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
                    index >= userViewModel.otpText.length -> ""
                    else -> userViewModel.otpText[index]
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


