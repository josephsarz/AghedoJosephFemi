package codegene.femicodes.aghedojosephfemi.di

import android.content.Context
import codegene.femicodes.aghedojosephfemi.local.dao.CarOwnerDao
import codegene.femicodes.aghedojosephfemi.local.dao.FilterDao
import codegene.femicodes.aghedojosephfemi.local.db.AppDatabase
import codegene.femicodes.aghedojosephfemi.remote.AppService
import codegene.femicodes.aghedojosephfemi.repository.FilterRemoteDataSource
import codegene.femicodes.aghedojosephfemi.repository.FilterRepository
import codegene.femicodes.aghedojosephfemi.ui.utils.BASE_API_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideDb(context: Context) = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideAppService(retrofit: Retrofit): AppService {
        return retrofit.create(AppService::class.java)
    }


    @Provides
    @Singleton
    fun provideFilterDao(appDatabase: AppDatabase): FilterDao {
        return appDatabase.filterDao()
    }

    @Provides
    @Singleton
    fun provideCarOwnerDao(appDatabase: AppDatabase): CarOwnerDao {
        return appDatabase.carOwnerDao()
    }

    @Singleton
    @Provides
    fun provideFilterRemoteDataSource(service: AppService): FilterRemoteDataSource {
        return FilterRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideFilterRepository(
        filterDao: FilterDao,
        carOwnerDao: CarOwnerDao,
        filterRemoteDataSource: FilterRemoteDataSource
    ): FilterRepository {
        return FilterRepository(filterDao, carOwnerDao, filterRemoteDataSource)
    }


}