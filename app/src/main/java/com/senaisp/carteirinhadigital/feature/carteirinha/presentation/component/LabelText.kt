package com.senaisp.carteirinhadigital.feature.carteirinha.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun LabelText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Right,
        modifier = modifier
    )
}