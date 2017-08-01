package de.fluchtwege.untitled.questions

import de.fluchtwege.untitled.models.Question
import io.reactivex.Single

class InMemoryQuestionsRepository(val questionsController: QuestionsController) : QuestionsRepository {

    var questions: List<Question> = emptyList()

    override fun getQuestions(lessonId: Int) = Single.concat(
            loadQuestions(),
            Single.just(questions))
            .filter { result -> !result.isEmpty() }
            .first(questions)


    private fun loadQuestions() = questionsController
            .getQuestions()
            .doOnSuccess { questions = it }

    override fun getQuestion(position: Int): Single<Question> {
        return Single.just(Question("", ""))
    }
}