package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel // Correct import
import com.example.weatherapp.WeatherViewModel
import androidx.compose.runtime.getValue // Correct import
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                val viewModel: WeatherViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherPage(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherPage(viewModel: WeatherViewModel, modifier: Modifier = Modifier) {
    val city = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val result by viewModel.weatherResult.observeAsState()

    Column(modifier = modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = city.value,
                onValueChange = { city.value = it },
                label = { Text("City") },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                viewModel.getData(city.value)
                keyboardController?.hide()
            }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (result) {
            is com.example.weatherapp.model.NetworkResponse.Loading -> {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is com.example.weatherapp.model.NetworkResponse.Error -> {
                val message = (result as com.example.weatherapp.model.NetworkResponse.Error).message
                Text(message, color = MaterialTheme.colorScheme.error)
            }
            is com.example.weatherapp.model.NetworkResponse.Success -> {
                val data = (result as com.example.weatherapp.model.NetworkResponse.Success<com.example.weatherapp.model.WeatherModel>).data
                WeatherDetails(data)
            }
            else -> {}
        }
    }
}

@Composable
fun WeatherDetails(data: com.example.weatherapp.model.WeatherModel) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.LocationOn, contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))
            Text("${data.location.name}, ${data.location.country}", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("${data.current.temp_c}°C", fontSize = 48.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        AsyncImage(
            model = data.current.condition.icon.replace("64x64", "128x128"),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Text(data.current.condition.text, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    WeatherKeyValue("Humidity", data.current.humidity)
                    WeatherKeyValue("Wind", data.current.wind_kph)
                    WeatherKeyValue("UV", data.current.uv)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    WeatherKeyValue("Precipitation", data.current.precip_mm)
                    WeatherKeyValue("Time", data.location.localtime.split(" ").getOrNull(1) ?: "-")
                    WeatherKeyValue("Date", data.location.localtime.split(" ").getOrNull(0) ?: "-")
                }
            }
        }
    }
}

@Composable
fun WeatherKeyValue(key: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(key, fontWeight = FontWeight.Bold)
        Text(value)
    }
}