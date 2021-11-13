package com.example.myapplication.api

import com.example.myapplication.util.Constants.Companion.BASE_URL
import com.example.myapplication.util.Constants.Companion.HANGANG_DETAILS_URL
import com.example.myapplication.util.Constants.Companion.HANGANG_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

    private val hangangInstance by lazy {
        Retrofit.Builder()
            .baseUrl(HANGANG_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(unsafeOkHttpClient().build())
            .build()
    }
    val hangangApi by lazy{
        hangangInstance.create(SimpleApi::class.java)
    }

    private val hangangDetailsInstance by lazy {
        Retrofit.Builder()
            .baseUrl(HANGANG_DETAILS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(unsafeOkHttpClient().build())
            .build()
    }
    val hangangDetailsApi by lazy{
        hangangDetailsInstance.create(SimpleApi::class.java)
    }

    // trustManager 해결
    private fun unsafeOkHttpClient(): OkHttpClient.Builder {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<out java.security.cert.X509Certificate>?,
                authType: String?
            ) {

            }

            override fun checkServerTrusted(
                chain: Array<out java.security.cert.X509Certificate>?,
                authType: String?
            ) {

            }

            override fun getAcceptedIssuers(): Array<out java.security.cert.X509Certificate>? {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { hostname, session -> true }

        return builder

    }
}