package com.example.fidofriendsidebar.ui.appointments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fidofriendsidebar.R

class MeetingsAdapter : RecyclerView.Adapter<MeetingsAdapter.MeetingViewHolder>() {
    private var meetings: List<Meeting> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.meeting_item, parent, false)
        return MeetingViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return meetings.size
    }

    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {
        val meeting = meetings[position]
        holder.bind(meeting)
    }

    fun submitList(meetings: List<Meeting>) {
        this.meetings = meetings
        notifyDataSetChanged()
    }

    inner class MeetingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val clientIdTextView: TextView = itemView.findViewById(R.id.clientIdTextView)

        fun bind(meeting: Meeting) {
            dateTextView.text = meeting.date
            clientIdTextView.text = "Client ID: ${meeting.clientId}"
        }
    }
}
