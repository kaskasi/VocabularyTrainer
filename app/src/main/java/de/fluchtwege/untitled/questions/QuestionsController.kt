package de.fluchtwege.untitled.questions

import de.fluchtwege.untitled.models.Question
import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.http.GET

class QuestionsController(val api: QuestionsApi, val scheduler: Scheduler) {


    fun getQuestions(): Single<List<Question>> {
        return api.getQuestions().subscribeOn(scheduler);
    }


    interface QuestionsApi {
        @GET("ocuhd")
        fun getQuestions() : Single<List<Question>>

    }
}