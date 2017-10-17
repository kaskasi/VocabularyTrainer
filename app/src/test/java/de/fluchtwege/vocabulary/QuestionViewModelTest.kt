package de.fluchtwege.vocabulary

import de.fluchtwege.vocabulary.questions.QuestionViewModel
import de.fluchtwege.vocabulary.models.Question
import org.junit.Assert
import org.junit.Test

class QuestionViewModelTest {

    val information = "info"
    val answer = "answer"

    @Test
    fun `Given question Then viewModel returns information`() {
        val question = Question(information, answer)
        val viewModel = QuestionViewModel(question)

        Assert.assertEquals(viewModel.getInformation(), information)
    }

    @Test
    fun `Given question Then viewModel returns answer`() {
        val question = Question(information, answer)
        val viewModel = QuestionViewModel(question)

        Assert.assertEquals(viewModel.getAnswer(), answer)
    }
}