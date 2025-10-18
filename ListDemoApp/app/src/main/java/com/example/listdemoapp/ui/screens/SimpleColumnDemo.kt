package com.example.listdemoapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimpleColumnDemo() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        (1..100).forEach { index ->
            Text(text = "Item $index")
        }
    }
}
