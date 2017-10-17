package de.fluchtwege.vocabulary

import de.fluchtwege.vocabulary.lessons.LessonViewModel
import de.fluchtwege.vocabulary.models.Lesson
import org.junit.Assert
import org.junit.Test

class LessonViewModelTest {

    val description = "description"
    val name = "name"

    @Test
    fun `Given lesson Then viewModel returns name`() {
        val lesson = Lesson(name, description, emptyList())
        val viewModel = LessonViewModel(lesson)

        Assert.assertEquals(viewModel.getName(), name)
    }

    @Test
    fun `Given lesson Then viewModel returns description`() {
        val lesson = Lesson(name, description, emptyList())
        val viewModel = LessonViewModel(lesson)

        Assert.assertEquals(viewModel.getDescription(), description)
    }
}