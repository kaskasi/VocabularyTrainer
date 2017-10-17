package de.fluchtwege.vocabulary.persistance

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.fluchtwege.vocabulary.models.Question

class Converters {

    @TypeConverter
    fun fromString(text: String): List<Question> {
        val type = object : TypeToken<ArrayList<Question>>() {}.type
        return Gson().fromJson<List<Question>>(text, type)
    }

    @TypeConverter
    fun fromList(questions: List<Question>): String {
        return Gson().toJson(questions)
    }
}