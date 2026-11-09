package com.myohteta.a.movieRecapStudio

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudioScreen()
        }
    }
}

@Composable
fun StudioScreen() {
    var videoUri by remember { mutableStateOf<Uri?>(null) }
    var brightness by remember { mutableFloatStateOf(0f) }
    var contrast by remember { mutableFloatStateOf(1f) }
    var exposure by remember { mutableFloatStateOf(0f) }
    var saturation by remember { mutableFloatStateOf(1f) }
    var temperature by remember { mutableFloatStateOf(0f) }
    var resolution by remember { mutableStateOf("1080p") }
    var ratio by remember { mutableStateOf("9:16 TikTok") }
    var voiceover by remember { mutableStateOf(true) }
    var subtitles by remember { mutableStateOf(true) }

    val videoPicker = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        videoUri = uri
    }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(18.dp)
        ) {
            Text(
                "AI MOVIE RECAP STUDIO",
                fontSize = 24.sp
            )

            Text("Professional Video Editor")

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { videoPicker.launch("video/*") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Select Video")
            }

            Text(
                videoUri?.lastPathSegment ?: "No video selected"
            )

            Spacer(Modifier.height(20.dp))

            Text("VIDEO ADJUSTMENT", fontSize = 18.sp)

            EditorSlider("Brightness", brightness, -1f, 1f) {
                brightness = it
            }

            EditorSlider("Contrast", contrast, 0f, 2f) {
                contrast = it
            }

            EditorSlider("Exposure", exposure, -2f, 2f) {
                exposure = it
            }

            EditorSlider("Saturation", saturation, 0f, 2f) {
                saturation = it
            }

            EditorSlider("Color Temperature", temperature, -1f, 1f) {
                temperature = it
            }

            Spacer(Modifier.height(16.dp))

            Text("ASPECT RATIO", fontSize = 18.sp)

            listOf(
                "9:16 TikTok",
                "16:9 YouTube",
                "1:1 Facebook"
            ).forEach { option ->
                Row {
                    RadioButton(
                        selected = ratio == option,
                        onClick = { ratio = option }
                    )
                    Text(option)
                }
            }

            Spacer(Modifier.height(16.dp))

            Text("EXPORT RESOLUTION", fontSize = 18.sp)

            listOf("720p", "1080p", "2K").forEach { option ->
                Row {
                    RadioButton(
                        selected = resolution == option,
                        onClick = { resolution = option }
                    )
                    Text(option)
                }
            }

            Spacer(Modifier.height(16.dp))

            Text("AUDIO & SUBTITLES", fontSize = 18.sp)

            Row {
                Checkbox(voiceover, { voiceover = it })
                Text("Original Voiceover")
            }

            Row {
                Checkbox(subtitles, { subtitles = it })
                Text("Subtitles")
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("EXPORT VIDEO")
            }

            Text(
                "AI Movie Recap Studio • Version 1.0",
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}

@Composable
fun EditorSlider(
    title: String,
    value: Float,
    min: Float,
    max: Float,
    onChange: (Float) -> Unit
) {
    Text("$title: ${"%.2f".format(value)}")

    Slider(
        value = value,
        onValueChange = onChange,
        valueRange = min..max
    )
}
