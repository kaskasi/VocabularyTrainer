package de.fluchtwege.untitled

import de.fluchtwege.untitled.lessons.LessonsRepository
import de.fluchtwege.untitled.models.Lesson
import de.fluchtwege.untitled.models.Question
import de.fluchtwege.untitled.questions.QuestionsViewModel
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*
import java.lang.Exception

class QuestionsViewModelTest {

    val lessonName = "name"
    val description = "description"

    val information = "information"
    val answer = "answer"

    @Test
    fun `Given repository When questions are loaded successfully Then view is notified`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val lesson = Lesson(lessonName, description, emptyList())
        doReturn(Flowable.just(lesson)).`when`(lessonsRepository).getLesson(lessonName)
        val viewModel = QuestionsViewModel(lessonName, lessonsRepository)

        var questionsLoaded = false
        viewModel.loadQuestions { questionsLoaded = true }

        assertTrue(questionsLoaded)
    }

    @Test
    fun `Given repository When questions are being loaded Then progress is shown`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        doReturn(Flowable.never<List<Lesson>>()).`when`(lessonsRepository).getLesson(lessonName)
        val viewModel = QuestionsViewModel(lessonName, lessonsRepository)

        viewModel.loadQuestions {  }

        assertTrue(viewModel.isProgressVisible())
    }

    @Test
    fun `Given repository When questions are loaded successfully Then progress is not shown`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val lesson = Lesson(lessonName, description, emptyList())
        doReturn(Flowable.just(lesson)).`when`(lessonsRepository).getLesson(lessonName)
        val viewModel = QuestionsViewModel(lessonName, lessonsRepository)

        viewModel.loadQuestions { }

        assertFalse(viewModel.isProgressVisible())
    }

    @Test
    fun `Given repository When questions can not be loaded Then progress is not shown`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        doReturn(Flowable.error<Exception>(Exception(""))).`when`(lessonsRepository).getLesson(lessonName)
        val viewModel = QuestionsViewModel(lessonName, lessonsRepository)

        viewModel.loadQuestions {  }

        assertFalse(viewModel.isProgressVisible())
    }

    @Test
    fun `Given repository When questions are loaded successfully Then number of questions is returned`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val questions = listOf(Question("", ""), Question("", ""))
        val lesson = Lesson(lessonName, description, questions)
        doReturn(Flowable.just(lesson)).`when`(lessonsRepository).getLesson(lessonName)
        val viewModel = QuestionsViewModel(lessonName, lessonsRepository)

        viewModel.loadQuestions { }

        assertEquals(viewModel.getNumberOfQuestions(), questions.size)
    }

    @Test
    fun `Given repository When questions are loaded successfully Then viewModel is created for question`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val questions = listOf(Question(information, answer), Question("", ""))
        val lesson = Lesson(lessonName, description, questions)
        doReturn(Flowable.just(lesson)).`when`(lessonsRepository).getLesson(lessonName)
        val viewModel = QuestionsViewModel(lessonName, lessonsRepository)

        viewModel.loadQuestions { }

        val questionViewModel = viewModel.getQuestionViewModel(0)

        assertEquals(questionViewModel.getInformation(), information)
        assertEquals(questionViewModel.getAnswer(), answer)
    }
}