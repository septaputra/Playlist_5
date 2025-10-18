package com.example.stateexample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyText(name: String, surname: String) {
    Text(
        text = "Hello $name $surname",
        style = TextStyle(fontSize = 30.sp),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun MyTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun StateTestScreen(modifier: Modifier = Modifier, viewModel: StateTestViewModel = viewModel()) {
    val name by viewModel.name.observeAsState(initial = "")
    val surname by viewModel.surname.observeAsState(initial = "")

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyText(name = name, surname = surname)

        MyTextField(
            value = name,
            onValueChange = { viewModel.onNameUpdate(it) },
            label = "Enter Name"
        )

        MyTextField(
            value = surname,
            onValueChange = { viewModel.onSurnameUpdate(it) },
            label = "Enter Surname"
        )
    }
}
