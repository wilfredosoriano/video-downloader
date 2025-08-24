package com.example.reelsdownloader

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reelsdownloader.ui.theme.ReelsDownloaderTheme

@Composable
fun ReelsDownloaderScreen(
    url: String,
    loading: Boolean,
    onUrlChanged: (String) -> Unit,
    onDownloadClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Main content at the top
        Column(
            modifier = Modifier.weight(1f), // take all available space above footer
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Video Downloader",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

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
                Text("Download",
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

@Composable
fun LoadingDialog(show: Boolean, inPreview: Boolean = false) {
    if (show) {
        AlertDialog(
            onDismissRequest = { /* Do nothing to block dismissal */ },
            confirmButton = {},
            title = {
                Text(
                    "Scraping… please wait",
                    style = MaterialTheme.typography.titleMedium.copy( // pick your base style
                        fontSize = 18.sp,        // adjust size
                        fontWeight = FontWeight.Bold, // make bold if desired
                        color = MaterialTheme.colorScheme.primary // or any Color
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

