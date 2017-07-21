package de.fluchtwege.untitled.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import de.fluchtwege.untitled.models.Lesson
import de.fluchtwege.untitled.vocabulary.LessonRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


@Module
class AppModule {

    private var retrofit: Retrofit? = null
    private val scheduler = Schedulers.io()

    @Provides
    fun provideRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .callbackExecutor(Executors.newCachedThreadPool())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .build()
        }
        return retrofit!!
    }



    @Provides
    fun provideLessonRepository(retrofit: Retrofit): LessonRepository {
        return object : LessonRepository {

            override fun getLession(position: Int): Single<Lesson> {
                return Single.just(Lesson("", ""))
            }

            override fun getLessons(): Single<List<Lesson>> {
                return Single.just(emptyList())
            }
        }
    }
}