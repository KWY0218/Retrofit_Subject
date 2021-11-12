package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.GoActivity
import com.example.myapplication.R
import com.example.myapplication.model.Lecture

class HanGangAdapter(val goActivity: GoActivity) : ListAdapter<Lecture, HanGangAdapter.LectureHolder>(LectureComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureHolder {
        return LectureHolder.create(parent)
    }

    override fun onBindViewHolder(holder: LectureHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.subjectName.toString(),current.professor.toString())
        holder.root.setOnClickListener{
            goActivity.goActivity(current.id)
        }
    }

    class LectureHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lectureName: TextView = itemView.findViewById(R.id.lecture_item_name)
        private val professorName: TextView = itemView.findViewById(R.id.professor_item)
        val root:ConstraintLayout = itemView.findViewById(R.id.root)
        fun bind(lecture: String?, professor:String?) {
            lectureName.text = lecture
            professorName.text = professor
        }

        companion object {
            fun create(parent: ViewGroup): LectureHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.lecture_item, parent, false)
                return LectureHolder(view)
            }
        }
    }

    class LectureComparator : DiffUtil.ItemCallback<Lecture>() {
        override fun areItemsTheSame(oldItem: Lecture, newItem: Lecture): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Lecture, newItem: Lecture): Boolean {
            return oldItem.id == newItem.id
        }
    }
}