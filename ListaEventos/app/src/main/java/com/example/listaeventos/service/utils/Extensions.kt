package com.example.listaeventos.service.utils

import android.widget.ImageView
import com.example.listaeventos.R
import com.example.listaeventos.service.constants.Constants.FORMATS.FORMAT_DATE
import com.example.listaeventos.service.constants.Constants.FORMATS.REGION
import com.example.listaeventos.service.constants.Constants.FORMATS.REGION_BR
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object Extensions {

    fun Long.toTimeDateString(): String {
        val dateTime = Date(this)
        val format = SimpleDateFormat(FORMAT_DATE, Locale.US)
        return format.format(dateTime)
    }

    fun Double.priceWithDecimal(): String {
        val myLocale = Locale(REGION, REGION_BR)
        val currency: NumberFormat = NumberFormat.getCurrencyInstance(myLocale)
        return currency.format(this)
    }

    fun bindPhoto(image: String, imageView: ImageView) {
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_error)
            .into(imageView)
    }
}