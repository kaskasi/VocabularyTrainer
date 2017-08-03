package de.fluchtwege.untitled

import de.fluchtwege.untitled.addlesson.AddLessonViewModel
import de.fluchtwege.untitled.lessons.LessonsRepository
import io.reactivex.Completable
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.*

class AddLessonViewModelTest {

    val lessonName = "name"
    val lessonDescription = "desc"

    @Test
    fun `Given repository Then lessonname is empty`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val viewModel = AddLessonViewModel(lessonsRepository)

        Assert.assertEquals(viewModel.lessonName, "")
    }

    @Test
    fun `Given repository When name is Entered Then lessonname is name`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val viewModel = AddLessonViewModel(lessonsRepository)

        viewModel.lessonName = lessonName

        Assert.assertEquals(viewModel.lessonName, lessonName)
    }

    @Test
    fun `Given repository Then description is empty`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val viewModel = AddLessonViewModel(lessonsRepository)

        Assert.assertEquals(viewModel.description, "")
    }

    @Test
    fun `Given repository When description is Entered Then lesson description is description`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        val viewModel = AddLessonViewModel(lessonsRepository)

        viewModel.description = lessonDescription

        Assert.assertEquals(viewModel.description, lessonDescription)
    }

    @Test
    fun `Given repository When lesson is saved Then repository saves lesson`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        doReturn(Completable.complete()).`when`(lessonsRepository).addLesson(TestUtil.anyObject())
        val viewModel = AddLessonViewModel(lessonsRepository)

        viewModel.description = lessonDescription
        viewModel.lessonName = lessonName
        viewModel.save({ })

        verify(lessonsRepository).addLesson(TestUtil.anyObject())
    }

    @Test
    fun `Given repository When lesson is saved Then onSaved is called`() {
        val lessonsRepository = mock(LessonsRepository::class.java)
        doReturn(Completable.complete()).`when`(lessonsRepository).addLesson(TestUtil.anyObject())
        val viewModel = AddLessonViewModel(lessonsRepository)

        viewModel.description = lessonDescription
        viewModel.lessonName = lessonName

        var savedCalled = false
        viewModel.save { savedCalled = true}

        Assert.assertTrue(savedCalled)
    }
}