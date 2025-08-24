package com.example.reelsdownloader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reelsdownloader.ui.theme.ReelsDownloaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ReelsViewModel = viewModel()
            val context = LocalContext.current

            ReelsDownloaderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ReelsDownloaderScreen(
                        url = viewModel.url.value,
                        loading = viewModel.loading.value,
                        onUrlChanged = viewModel::onUrlChanged,
                        onDownloadClick = { viewModel.downloadReel(context) },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
