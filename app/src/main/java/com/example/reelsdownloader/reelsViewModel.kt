package com.example.reelsdownloader

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import android.app.DownloadManager
import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import java.io.File


class ReelsViewModel : ViewModel() {
    private val _url = mutableStateOf("")
    val url: State<String> = _url

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    fun onUrlChanged(newUrl: String) {
        _url.value = newUrl
    }

    fun downloadReel(context: Context) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = RetrofitClient.api.downloadVideo(DownloadRequest(url.value))
                val videoUrl = response.videoUrl
                val fileName = "downloaded_video_${System.currentTimeMillis()}.mp4"

                val request = DownloadManager.Request(Uri.parse(videoUrl))
                    .setTitle("VidSaver")
                    .setDescription("Downloading Video...")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)

                val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                dm.enqueue(request)

                Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show()

                // Scan so it appears in gallery
                val file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    fileName
                )
                MediaScannerConnection.scanFile(
                    context,
                    arrayOf(file.absolutePath),
                    arrayOf("video/mp4"),
                    null
                )
            } catch (e: Exception) {
                Toast.makeText(context, "Failed: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                _loading.value = false
                _url.value = ""
            }
        }
    }
}

