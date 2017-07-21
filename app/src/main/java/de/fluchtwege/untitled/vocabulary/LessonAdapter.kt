package de.fluchtwege.untitled.vocabulary

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class LessonAdapter(val viewModel: LessonsViewModel) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LessonViewHolder {
        return LessonViewHolder(null)
    }

    override fun onBindViewHolder(holder: LessonViewHolder?, position: Int) {

    }

    override fun getItemCount() = viewModel.getNumberOfLessons()

    class LessonViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}