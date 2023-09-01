package com.example.hw7_bookstore

import android.app.Application
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.hw7_bookstore.common.Constants.BASE_URL
import com.example.hw7_bookstore.data.source.remote.ProductService
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class MainApplication : Application()