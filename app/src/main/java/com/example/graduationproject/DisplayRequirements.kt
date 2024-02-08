package com.example.graduationproject

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun DisplayRequirements(
    isFieldFocused: Boolean,
    requirements: List<Pair<Int, (String) -> Boolean>>,
    fieldValue: String
) {
    if (isFieldFocused) {
        requirements.forEach { requirement ->
            val (descriptionId, predicate) = requirement
            val fulfill = predicate(fieldValue)

            Text(
                text = if (fulfill) "✅ ${stringResource(descriptionId)}"
                else "❌ ${stringResource(descriptionId)}",
                color = if (fulfill) Color.Green else Color.Red
            )
        }
    }
}
