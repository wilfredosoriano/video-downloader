package com.example.reelsdownloader

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/download")
    suspend fun downloadVideo(@Body request: DownloadRequest): DownloadResponse
}

data class DownloadRequest(val url: String)
data class DownloadResponse(val videoUrl: String, val title: String)
