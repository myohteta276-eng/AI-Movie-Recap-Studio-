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
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        videoUri = uri
    }

    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                "AI Movie Recap Studio",
                fontSize = 26.sp
            )

            HorizontalDivider()

            Text("Video Selection", fontSize = 20.sp)

            Text(
                if (videoUri == null)
                    "ဗီဒီယို မရွေးရသေးပါ"
                else
                    "ဗီဒီယို ရွေးချယ်ပြီးပါပြီ"
            )

            Button(
                onClick = {
                    videoPicker.launch("video/*")
                }
            ) {
                Text("SELECT VIDEO")
            }

            HorizontalDivider()

            Text("Video Adjustments", fontSize = 20.sp)

            Text("Brightness: ${brightness.toInt()}")

            Slider(
                value = brightness,
                onValueChange = { brightness = it },
                valueRange = -100f..100f
            )

            Text("Contrast: ${"%.1f".format(contrast)}")

            Slider(
                value = contrast,
                onValueChange = { contrast = it },
                valueRange = 0f..2f
            )

            Text("Exposure: ${exposure.toInt()}")
Slider(
    value = exposure,
    onValueChange = { exposure = it },
    valueRange = -100f..100f
)

Text("Saturation: ${"%.1f".format(saturation)}")

Slider(
    value = saturation,
    onValueChange = { saturation = it },
    valueRange = 0f..2f
)
Text("Color Temperature: ${temperature.toInt()}")

Slider(
    value = temperature,
    onValueChange = { temperature = it },
    valueRange = -100f..100f
)

HorizontalDivider()

Text("Export Settings", fontSize = 20.sp)

Text("Resolution: $resolution")

Row(
    horizontalArrangement = Arrangement.spacedBy(8.dp)
) {
    listOf("720p", "1080p", "2K").forEach {
        OutlinedButton(onClick = { resolution = it }) {
            Text(it)
        }
    }
}

Text("Aspect Ratio: $ratio")

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

HorizontalDivider()

Row {
    Checkbox(
        checked = voiceover,
        onCheckedChange = { voiceover = it }
    )
    Text("Voiceover")
}

Row {
    Checkbox(
        checked = subtitles,
        onCheckedChange = { subtitles = it }
    )
    Text("Subtitles")
}

Button(
    onClick = { },
    modifier = Modifier.fillMaxWidth()
) {
    Text("EXPORT VIDEO")
}

Text(
    "လက်ရှိအဆင့်မှာ UI နဲ့ Setting များသာ အလုပ်လုပ်ပါသေးသည်။"
)

        }
    }
}
