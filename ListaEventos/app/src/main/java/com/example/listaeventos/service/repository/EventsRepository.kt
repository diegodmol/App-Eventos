package com.example.listaeventos.service.repository

import android.content.Context
import com.example.listaeventos.R
import com.example.listaeventos.service.constants.Constants
import com.example.listaeventos.service.model.EventsModel
import com.example.listaeventos.service.model.ReturnModel
import com.example.listaeventos.service.utils.APIResponse
import com.example.listaeventos.service.utils.RetroFitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsRepository(val context: Context) {
   private val remote = RetroFitClient.getService(EventsService::class.java)

    fun getEvents(responseReturn:APIResponse<List<EventsModel>>) {
       val call =  remote.getEvents()
        call.enqueue(object : Callback<List<EventsModel>>{
            override fun onResponse(call: Call<List<EventsModel>>, response: Response<List<EventsModel>>
            ) {
                if (response.code() == Constants.REQUESTHTTP.SUCCESS) {
                    response.body()?.let { responseReturn.onSuccess(it) }
                } else {
                    responseReturn.onFailure(failedResponse(response.errorBody()!!.string()))
                }
            }
            override fun onFailure(call: Call<List<EventsModel>>, t: Throwable) {
                responseReturn.onFailure(context.getString(R.string.UNEXPECTED_ERROR))
            }
        })
    }

    fun postCheckinEvent(eventId : String,name : String, email : String, responseReturn:APIResponse<ReturnModel>) {
        val call =  remote.postCheckin(eventId,name,email)
        call.enqueue(object : Callback<ReturnModel>{
            override fun onResponse(call: Call<ReturnModel>, response: Response<ReturnModel>) {
                if (response.body()?.code.equals(Constants.REQUESTHTTP.SUCCESS.toString())){
                    response.body()?.let { responseReturn.onSuccess(it)}
                }else{
                    responseReturn.onFailure(failedResponse(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<ReturnModel>, t: Throwable) {
                responseReturn.onFailure(context.getString(R.string.UNEXPECTED_ERROR))
            }

        })

    }

    private  fun failedResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

}