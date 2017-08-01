package de.fluchtwege.untitled.questions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import de.fluchtwege.untitled.databinding.ItemQuestionBinding


class QuestionsAdaper(val viewModel: QuestionsViewModel) : RecyclerView.Adapter<QuestionsAdaper.QuestionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ItemQuestionBinding.inflate(inflater, parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder?, position: Int) {
        val questionViewModel = viewModel.getQuestionViewModel(position)
        holder?.binding?.viewModel = questionViewModel
    }

    override fun getItemCount() = viewModel.getNumberOfQuestions()

    class QuestionViewHolder(val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root)
}