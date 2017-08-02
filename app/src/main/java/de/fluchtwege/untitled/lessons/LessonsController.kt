package de.fluchtwege.untitled.lessons

import de.fluchtwege.untitled.models.Lesson
import de.fluchtwege.untitled.persistance.RepositoryUrl
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Url

class LessonsController(val lessonApi: LessonsApi, val repositoryUrl: RepositoryUrl) {

    fun getLessons(): Flowable<List<Lesson>> {
        return lessonApi.getLessons(repositoryUrl.getUrl())
    }

    interface LessonsApi {

        @GET
        fun getLessons(@Url url: String): Flowable<List<Lesson>>

    }
}