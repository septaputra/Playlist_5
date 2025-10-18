package com.example.learnnavigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ScreenA(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Cristiano Ronaldo (Manchester United)",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Bersinar pada periode 2003–2009, membawa MU meraih 3 gelar Liga Inggris, 1 Liga Champions (2008), dan memenangkan Ballon d’Or pertamanya pada 2008.",
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )

            Button(onClick = { navController.navigate(Routes.ScreenB + "/Lionel Messi") }) {
                Text(text = "Pindah ke Screen B")
            }
        }
    }
}

@Composable
fun ScreenB(name: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Lionel Messi (Barcelona)",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Menjadi bintang utama 2004–2021, memenangkan 10 La Liga, 4 Liga Champions, dan meraih 6 Ballon d’Or, sekaligus pencetak gol terbanyak sepanjang masa klub.",
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )

            Text(
                text = "Nama yang dikirim: $name",
                textAlign = TextAlign.Center
            )
        }
    }
}
