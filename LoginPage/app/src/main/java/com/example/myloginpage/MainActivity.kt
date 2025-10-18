package com.example.myloginpage

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myloginpage.ui.theme.MyLoginPageTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyLoginPageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bagian Header
        Image(
            painter = painterResource(id = R.drawable.login_logo),
            contentDescription = "Login Logo",
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Welcome Back",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Log in to your account")

        // Bagian Input
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        )

        // Bagian Aksi
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Log.i("CREDENTIALS", "Email: ${emailState.value}, Password: ${passwordState.value}")
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        ) {
            Text("LOGIN")
        }
        Spacer(modifier = Modifier.height(32.dp))
        TextButton(onClick = {
            Log.i("ACTION", "Forgot Password clicked")
        }) {
            Text("Forgot Password?")
        }

        // Bagian Social Login
        Spacer(modifier = Modifier.height(32.dp))
        Text("Or sign in with")
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_facebook),
                contentDescription = "Facebook",
                modifier = Modifier.size(60.dp).clickable {
                    Log.i("SOCIAL", "Facebook clicked")
                }
            )
            Image(
                painter = painterResource(id = R.drawable.logo_google),
                contentDescription = "Google",
                modifier = Modifier.size(60.dp).clickable {
                    Log.i("SOCIAL", "Google clicked")
                }
            )
            Image(
                painter = painterResource(id = R.drawable.logo_x),
                contentDescription = "X",
                modifier = Modifier.size(60.dp).clickable {
                    Log.i("SOCIAL", "X clicked")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MyLoginPageTheme {
        LoginScreen()
    }
}
