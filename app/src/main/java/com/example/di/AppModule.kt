package com.example.di

import com.example.data.network.APIService
import com.example.data.repository.MenuRepository
import com.example.data.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }
                )
                .build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideRestaurantRepository(apiService: APIService) : RestaurantRepository {
        return  RestaurantRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideMenuRepository(apiService: APIService) : MenuRepository {
        return  MenuRepository(apiService)
    }
//    private const val BASE_URL : String = "http://localhost:3000/"
    private const val BASE_URL : String = "http://10.0.2.2:3000/"
    //http://localhost:3000/restaurants
}