package de.fluchtwege.untitled

import de.fluchtwege.untitled.models.Lesson
import de.fluchtwege.untitled.lessons.LessonViewModel
import org.junit.Assert
import org.junit.Test

class LessonViewModelTest {

    val description = "description"
    val name = "name"

    @Test
    fun `Given lesson Then viewModel returns name`() {
        val lesson = Lesson(name, description)
        val viewModel = LessonViewModel(lesson)

        Assert.assertEquals(viewModel.getName(), name)
    }

    @Test
    fun `Given lesson Then viewModel returns description`() {
        val lesson = Lesson(name, description)
        val viewModel = LessonViewModel(lesson)

        Assert.assertEquals(viewModel.getDescription(), description)
    }
}