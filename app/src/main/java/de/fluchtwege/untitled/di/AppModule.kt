package de.fluchtwege.untitled.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import de.fluchtwege.untitled.Untitled
import de.fluchtwege.untitled.questions.QuestionsController
import de.fluchtwege.untitled.lessons.LessonsRepository
import de.fluchtwege.untitled.lessons.LessonsController
import de.fluchtwege.untitled.lessons.RoomLessonRepository
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
                    .baseUrl("https://api.myjson.com/bins/")
                    .build()
        }
        return retrofit!!
    }

    @Provides
    fun provideQuestionsController(retrofit: Retrofit): QuestionsController {
        val questionApi = retrofit.create(QuestionsController.QuestionsApi::class.java)
        return QuestionsController(questionApi, scheduler)
    }

    @Provides
    fun provideLessonsController(retrofit: Retrofit): LessonsController {
        val lessonsApi = retrofit.create(LessonsController.LessonsApi::class.java)
        return LessonsController(lessonsApi, scheduler)
    }

    @Provides
    fun provideLessonsRepository(lessonsController: LessonsController): LessonsRepository {
        return RoomLessonRepository(lessonsController, Untitled.database)
    }
}