package de.fluchtwege.vocabulary.persistance

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import de.fluchtwege.vocabulary.models.Lesson

@Database(entities = arrayOf(Lesson::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UntitledDatabase : RoomDatabase() {

    abstract fun lessonDao(): LessonDao

}