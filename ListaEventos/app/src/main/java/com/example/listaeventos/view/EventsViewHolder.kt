package com.example.listaeventos.view

import androidx.recyclerview.widget.RecyclerView
import com.example.listaeventos.service.model.EventsModel
import com.example.listaeventos.service.utils.Extensions.toTimeDateString
import com.example.listaeventos.databinding.FragmentEventsListBinding
import com.example.listaeventos.service.listener.EventListener
import com.example.listaeventos.service.utils.Extensions.bindPhoto

class EventsViewHolder(private val itemBinding: FragmentEventsListBinding, val listener : EventListener) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bindData(event: EventsModel) {
        itemBinding.itemTitle.text = event.title
        itemBinding.itemDate.text = event.date.toTimeDateString()
        itemBinding.itemDescription.text = event.description
        bindPhoto(event.image, itemBinding.itemImage)

        itemBinding.btnShowDetails.setOnClickListener {
            listener.onClickEvent(event)
        }
    }
}