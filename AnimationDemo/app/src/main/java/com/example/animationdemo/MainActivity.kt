package com.example.animationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animationdemo.ui.theme.AnimationDemoTheme
import kotlinx.coroutines.launch
import kotlin.math.pow

const val DURATION_MS = 2000

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SimpleAnimationPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Buat versi Compose dari OvershootInterpolator
val OvershootEasing: Easing = Easing { fraction ->
    val tension = 2.0f
    val t = fraction - 1.0f
    t * t * ((tension + 1) * t + tension) + 1.0f
}

@Composable
fun SimpleAnimationPage(modifier: Modifier = Modifier) {
    val animateTrigger: MutableState<Boolean> = remember { mutableStateOf(false) }
    val scale = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(animateTrigger.value) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = DURATION_MS,
                easing = OvershootEasing
            )
        )
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = DURATION_MS,
                easing = OvershootEasing
            )
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value,
                    rotationZ = rotation.value
                )
        )
        Button(onClick = {
            scope.launch {
                scale.snapTo(0f)
                rotation.snapTo(0f)
            }
            animateTrigger.value = !animateTrigger.value
        }) {
            Text("Animate Again")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleAnimationPagePreview() {
    AnimationDemoTheme {
        SimpleAnimationPage()
    }
}
