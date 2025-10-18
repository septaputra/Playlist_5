package com.example.mycalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.mycalculator.ui.theme.MyCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Kalkulator",
            fontSize = 18.sp,
            color = androidx.compose.ui.graphics.Color.Black,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Number 1") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Number 2") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                val result = num1.toInt() + num2.toInt()
                Toast.makeText(context, "Hasilnya adalah $result", Toast.LENGTH_SHORT).show()
            }) {
                Text("TAMBAH")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                val result = num1.toInt() - num2.toInt()
                Toast.makeText(context, "Hasilnya adalah $result", Toast.LENGTH_SHORT).show()
            }) {
                Text("KURANG")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                val result = num1.toInt() * num2.toInt()
                Toast.makeText(context, "Hasilnya adalah $result", Toast.LENGTH_SHORT).show()
            }) {
                Text("KALI")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                val result = num1.toInt() / num2.toInt()
                Toast.makeText(context, "Hasilnya adalah $result", Toast.LENGTH_SHORT).show()
            }) {
                Text("BAGI")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    MyCalculatorTheme {
        CalculatorScreen()
    }
}