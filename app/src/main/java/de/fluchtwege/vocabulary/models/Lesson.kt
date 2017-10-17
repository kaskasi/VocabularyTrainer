package de.fluchtwege.vocabulary.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Lesson(@PrimaryKey val name: String, val description: String, var questions: List<Question>)