package com.example.reelsdownloader

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reelsdownloader.ui.theme.ReelsDownloaderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReelsDownloaderScreen(
    url: String,
    loading: Boolean,
    onUrlChanged: (String) -> Unit,
    onDownloadClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("VidSaver",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp) },
                actions = {
                    IconButton(onClick = { shareApp(context) }) {
                        Icon(Icons.Default.Share, contentDescription = "Share App",
                            tint = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(
                modifier = Modifier.weight(1f)
                .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = url,
                    onValueChange = onUrlChanged,
                    label = { Text("Paste YT, IG, and FB Link") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDownloadClick,
                    enabled = !loading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Download",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

                LoadingDialog(show = loading)
            }

            // Footer pinned to the bottom of the screen
            Text(
                text = "© 2025 Wilfredo Jr.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp, bottom = 4.dp)
            )
        }
    }
}

@Composable
fun LoadingDialog(show: Boolean, inPreview: Boolean = false) {
    if (show) {
        AlertDialog(
            onDismissRequest = { /*  */ },
            confirmButton = {},
            title = {
                Text(
                    "Scraping… please wait",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                }
            }
        )
    }
}

fun shareApp(context: Context) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "Check out this app! Download here: https://github.com/wilfredosoriano/video-downloader/releases/latest/download/app-debug.apk"
        )
        type = "text/plain"
    }
    context.startActivity(
        Intent.createChooser(shareIntent, "Share via")
    )
}


@Preview(showBackground = true)
@Composable
fun ReelsDownloaderScreenPreview() {
    ReelsDownloaderTheme {
        ReelsDownloaderScreen(
            url = "https://example.com",
            loading = false,
            onUrlChanged = {},
            onDownloadClick = {}
        )
    }
}

