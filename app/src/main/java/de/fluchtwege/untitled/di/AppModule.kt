package de.fluchtwege.untitled.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import de.fluchtwege.untitled.Untitled
import de.fluchtwege.untitled.lessons.LessonsController
import de.fluchtwege.untitled.lessons.LessonsRepository
import de.fluchtwege.untitled.lessons.RoomLessonsRepository
import de.fluchtwege.untitled.persistance.RepositoryId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

@Module
class AppModule(val untitled: Untitled) {

    private val baseUrl = "https://api.myjson.com/"
    private val subscribeOn = Schedulers.io()
    private val observeOn = AndroidSchedulers.mainThread()

    private var retrofit: Retrofit? = null

    @Provides
    fun provideRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .callbackExecutor(Executors.newCachedThreadPool())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createHttpClient())
                    .baseUrl(baseUrl)
                    .build()
        }
        return retrofit!!
    }

    private fun createHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val logLevel = HttpLoggingInterceptor.Level.BODY
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = logLevel
        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    fun provideRepositoryUrl(): RepositoryId {
        return RepositoryId(untitled)
    }

    @Provides
    fun provideLessonsController(retrofit: Retrofit, repositoryId: RepositoryId): LessonsController {
        val lessonsApi = retrofit.create(LessonsController.LessonsApi::class.java)
        return LessonsController(lessonsApi, repositoryId)
    }

    @Provides
    fun provideLessonsRepository(lessonsController: LessonsController): LessonsRepository {
        return RoomLessonsRepository(lessonsController, untitled.database, observeOn, subscribeOn)
    }


}