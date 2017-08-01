package de.fluchtwege.untitled.questions

import de.fluchtwege.untitled.models.Question
import io.reactivex.Single

interface QuestionsRepository {

    fun getQuestions(lessonId: Int): Single<List<Question>>

    fun getQuestion(position: Int): Single<Question>

}