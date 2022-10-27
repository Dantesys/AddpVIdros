package com.dantesys.addpvidros.data.di

import android.content.Context
import android.util.Log
import com.dantesys.addpvidros.data.services.AuthIntecerptors
import com.dantesys.addpvidros.data.services.UserService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModel {
    private const val TAG = "HTTP"
    fun load(){
        loadKoinModules(networkModules())
    }
    private fun networkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(TAG,"NetworkModules:\n$it")
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }
            single {
                getService<UserService>(get())
            }
        }
    }
    private inline fun <reified T> getService(context: Context): T{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(context))
            .build()
        return retrofit.create(T::class.java)
    }
    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthIntecerptors(context))
            .build()
    }
}