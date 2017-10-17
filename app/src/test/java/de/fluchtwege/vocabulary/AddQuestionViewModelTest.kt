package de.fluchtwege.vocabulary

import de.fluchtwege.vocabulary.addquestion.AddQuestionViewModel
import de.fluchtwege.vocabulary.lessons.LessonsRepository
import io.reactivex.Completable
import org.junit.After
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mockito.*


class AddQuestionViewModelTest {

    val newQuestionPosition = -1
    val information = "information"
    val answer = "answer"
    val lessonName = "name"

    @Test
    fun `Given repository Then question is empty`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val viewModel = AddQuestionViewModel(lessonsRepository, lessonName, 0)

        Assert.assertEquals(viewModel.information, "")
    }

    @Test
    fun `Given repository When question is entered Then question is entered value`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val viewModel = AddQuestionViewModel(lessonsRepository, lessonName, 0)

        viewModel.information = information

        Assert.assertEquals(viewModel.information, information)
    }

    @Test
    fun `Given repository Then description is empty`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val viewModel = AddQuestionViewModel(lessonsRepository, lessonName, 0)

        Assert.assertEquals(viewModel.answer, "")
    }

    @Test
    fun `Given repository When description is Entered Then lesson description is entered value`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val viewModel = AddQuestionViewModel(lessonsRepository, lessonName, 0)

        viewModel.answer = answer

        Assert.assertEquals(viewModel.answer, answer)
    }

    @Test
    fun `Given repository When question is saved Then repository saves question`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        doReturn(Completable.complete()).`when`(lessonsRepository).addQuestion(TestUtil.anyObject(), TestUtil.anyObject())
        val viewModel = AddQuestionViewModel(lessonsRepository, lessonName, newQuestionPosition)

        viewModel.answer = answer
        viewModel.information = information
        viewModel.save({ })

        verify(lessonsRepository).addQuestion(TestUtil.anyObject(), TestUtil.anyObject())
    }

    @Test
    fun `Given repository When question is saved Then onSaved is called`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        doReturn(Completable.complete()).`when`(lessonsRepository).addQuestion(TestUtil.anyObject(), TestUtil.anyObject())
        val viewModel = AddQuestionViewModel(lessonsRepository, lessonName, newQuestionPosition)

        viewModel.information = information
        viewModel.answer = answer

        var savedCalled = false
        viewModel.save { savedCalled = true }

        Assert.assertTrue(savedCalled)
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}