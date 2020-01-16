package aliabbas.com.codebaseapp.di_dependencies.app_dependencies

import aliabbas.com.codebaseapp.app_service_call.Api
import aliabbas.com.codebaseapp.app_service_call.AppServiceBuilder
import aliabbas.com.codebaseapp.models.pixabaymodels.PixaBayDataSource
import aliabbas.com.codebaseapp.utils.Constants.Companion.BASE_URL
import aliabbas.com.codebaseapp.viewmodels.pixabay_home.ViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created By Ali Abbas on on 12,January,2020
 * This Class is used for
 */
@Module
class ApiServiceModule {
    @get:Provides
    @Singleton
    val requestHeader: OkHttpClient
        get() {
            val MULTIPART_FORM = "multipart/form-dat"
            val APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8"
            val httpClient = OkHttpClient.Builder()
            httpClient.retryOnConnectionFailure(true)
            httpClient.addInterceptor { chain ->
                val response = chain.proceed(chain.request())
                response.header("Content-ReportCopy", APPLICATION_JSON_CHARSET_UTF_8)
                response.header("Accept", APPLICATION_JSON_CHARSET_UTF_8)
                response.header("Connection", "close")
                response
            }.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
            return httpClient.build()
        }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient?): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .create()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build().create(Api::class.java)
    }

    @Provides
    @Singleton
    fun getRepository(apiCallInterface: Api?): AppServiceBuilder {
        return AppServiceBuilder(apiCallInterface!!)
    }

    @Provides
    @Singleton
    fun getViewModelFactory(
        myRepository: AppServiceBuilder?, pixaBayDataSource: PixaBayDataSource?
    ): ViewModelProvider.Factory {
        return ViewModelFactory(myRepository, pixaBayDataSource)
    }
}