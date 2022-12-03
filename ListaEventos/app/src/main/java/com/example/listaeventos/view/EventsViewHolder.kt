package com.example.listaeventos.view

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.listaeventos.databinding.FragmentEventsListBinding
import com.example.listaeventos.service.model.EventsModel
import com.example.listaeventos.service.listener.EventListener
import com.example.listaeventos.service.utils.Extensions.bindPhoto

class EventsViewHolder(
    private val itemBinding: FragmentEventsListBinding,
    val listener: EventListener
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bindData(event: EventsModel) {
        itemBinding.txtInformation.text = event.title
        bindPhoto(event.image, itemBinding.itemImage)

        itemBinding.itemImage.setOnClickListener {
            listener.onClickEvent(event)
        }

        itemBinding.imgShare.setOnClickListener {
            shareContent(event.image)
        }

        itemBinding.imgFavorite.setOnClickListener{
            itemBinding.imgFavorite.visibility = View.GONE
            itemBinding.imgFavoriteLike.visibility = View.VISIBLE
        }

        itemBinding.imgFavoriteLike.setOnClickListener{
            itemBinding.imgFavorite.visibility = View.VISIBLE
            itemBinding.imgFavoriteLike.visibility = View.GONE
        }
    }

    private fun shareContent(item: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, item)
        intent.type = "text/plain"
        itemView.context?.startActivity(Intent.createChooser(intent, "Compartilhar em:"))
    }
}