package de.fluchtwege.untitled.lessons

import de.fluchtwege.untitled.models.Lesson
import io.reactivex.Flowable
import io.reactivex.Scheduler
import retrofit2.http.GET

class LessonsController(val lessonApi: LessonsApi, val scheduler: Scheduler) {

    fun getLessons(): Flowable<List<Lesson>> {
        return lessonApi.getLessons().subscribeOn(scheduler)
    }

    interface LessonsApi {
        @GET("ocuhd")
        fun getLessons(): Flowable<List<Lesson>>


    }
}