package de.fluchtwege.untitled.lesson

import de.fluchtwege.untitled.models.Question
import io.reactivex.Single

interface QuestionRepository {

    fun getQuestions(lessonId: Int): Single<List<Question>>

}