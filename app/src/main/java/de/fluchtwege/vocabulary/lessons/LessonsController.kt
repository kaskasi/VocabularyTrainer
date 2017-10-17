package de.fluchtwege.vocabulary.lessons

import de.fluchtwege.vocabulary.models.Lesson
import de.fluchtwege.vocabulary.persistance.RepositoryId
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

class LessonsController(val lessonApi: LessonsApi, val repositoryId: RepositoryId) {

    fun getLessons(): Flowable<List<Lesson>> {
        return lessonApi.getLessons(repositoryId.getId())
    }

    fun storeLessons(lessons: List<Lesson>): Completable {
        return lessonApi.storeLessons(repositoryId.getId(), lessons)
    }

    interface LessonsApi {

        @PUT("/bins/{repoId}")
        fun storeLessons(@Path("repoId") repoId: String, @Body lessons: List<Lesson>): Completable

        @GET("bins/{repoId}")
        fun getLessons(@Path("repoId") repoId: String): Flowable<List<Lesson>>

    }

}