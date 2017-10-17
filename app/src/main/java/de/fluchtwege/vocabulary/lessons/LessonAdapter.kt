package de.fluchtwege.vocabulary.lessons

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import de.fluchtwege.vocabulary.databinding.ItemLessonBinding
import de.fluchtwege.vocabulary.questions.QuestionsActivity
import de.fluchtwege.vocabulary.questions.QuestionsFragment

class LessonAdapter(val viewModel: LessonsViewModel, val context: Context) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemLessonBinding.inflate(inflater, parent, false)
        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lessonViewModel = viewModel.getLessonViewModel(position)
        holder.binding.viewModel = lessonViewModel
        holder.binding.root.setOnClickListener { openQuestions(position) }
    }

    private fun openQuestions(position: Int) {
        val openQuestions = Intent(context, QuestionsActivity::class.java)
        val lessonName = viewModel.getLessonName(position)
        openQuestions.putExtra(QuestionsFragment.KEY_LESSON_NAME, lessonName)
        context.startActivity(openQuestions)
    }

    override fun getItemCount() = viewModel.getNumberOfLessons()

    class LessonViewHolder(val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root)
}