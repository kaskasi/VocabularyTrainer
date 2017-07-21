package de.fluchtwege.untitled.lesson

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


class QuestionAdaper(val viewModel: QuestionsViewModel) : RecyclerView.Adapter<QuestionAdaper.QuestionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(null)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder?, position: Int) {

    }

    override fun getItemCount() = viewModel.getNumberOfQuestions()

    class QuestionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}