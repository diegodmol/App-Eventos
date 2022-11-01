package com.example.listaeventos.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listaeventos.view.EventsViewHolder
import com.example.listaeventos.databinding.FragmentEventsListBinding
import com.example.listaeventos.service.listener.EventListener
import com.example.listaeventos.service.model.EventsModel

class EventsAdapter(list: List<EventsModel>) : RecyclerView.Adapter<EventsViewHolder>() {

    private var listEvents = list
    private lateinit var listener: EventListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = FragmentEventsListBinding.inflate(inflater, parent, false)
        return EventsViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bindData(listEvents[position])
    }

    override fun getItemCount(): Int {
        return listEvents.size
    }

    fun attachListener(eventListener: EventListener) {
        listener = eventListener
    }

}