package de.fluchtwege.untitled.persistance

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import de.fluchtwege.untitled.models.Lesson
import io.reactivex.Flowable

@Dao
interface LessonDao {

    @Query("SELECT * FROM lesson")
    fun getLessons(): Flowable<List<Lesson>>

    @Query("SELECT * FROM lesson WHERE name = :name LIMIT 1")
    fun getLesson(name: String): Flowable<Lesson>

    @Query("DELETE FROM lesson")
    fun clearLessons(): Unit

    @Insert
    fun insert(lesson: Lesson): Unit

}