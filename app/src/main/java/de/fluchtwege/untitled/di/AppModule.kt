package de.fluchtwege.untitled.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import de.fluchtwege.untitled.Untitled
import de.fluchtwege.untitled.lessons.LessonsController
import de.fluchtwege.untitled.lessons.LessonsRepository
import de.fluchtwege.untitled.lessons.RoomLessonRepository
import de.fluchtwege.untitled.persistance.RepositoryUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


@Module
class AppModule(val untitled: Untitled) {

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
                    .client(OkHttpClient.Builder().build())
                    .baseUrl("https://api.myjson.com/bins/")
                    .build()
        }
        return retrofit!!
    }

    @Provides
    fun provideRepositoryUrl(): RepositoryUrl {
        return RepositoryUrl(untitled)
    }

    @Provides
    fun provideLessonsController(retrofit: Retrofit, repositoryUrl: RepositoryUrl): LessonsController {
        val lessonsApi = retrofit.create(LessonsController.LessonsApi::class.java)
        return LessonsController(lessonsApi, repositoryUrl)
    }

    @Provides
    fun provideLessonsRepository(lessonsController: LessonsController): LessonsRepository {
        return RoomLessonRepository(lessonsController, untitled.database, observeOn, subscribeOn)
    }


}