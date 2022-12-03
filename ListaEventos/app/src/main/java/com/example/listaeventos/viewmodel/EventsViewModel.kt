package com.example.listaeventos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listaeventos.service.model.EventsModel
import com.example.listaeventos.service.model.ReturnModel
import com.example.listaeventos.service.repository.EventsRepository
import com.example.listaeventos.service.utils.APIResponse

class EventsViewModel(application: Application) : AndroidViewModel(application) {

    private val eventsRepository = EventsRepository(application.applicationContext)
    private val _events = MutableLiveData<List<EventsModel>>()
    val events: LiveData<List<EventsModel>> = _events
    private val _eventsCheckin = MutableLiveData<ReturnModel>()
    val eventsCheckIn: LiveData<ReturnModel> = _eventsCheckin


    private val _failure = MutableLiveData<String>()
    val failure: LiveData<String> = _failure

    fun getEvents() {
        eventsRepository.getEvents(object : APIResponse<List<EventsModel>> {
            override fun onSuccess(result: List<EventsModel>) {
                _events.value = result
            }

            override fun onFailure(message: String) {
                _failure.value = message
            }
        })
    }

    fun postCheckInEvents(eventId: String, name: String, email: String) {
        eventsRepository.postCheckinEvent(eventId, name, email, object : APIResponse<ReturnModel> {
            override fun onSuccess(result: ReturnModel) {
                _eventsCheckin.value = result
            }

            override fun onFailure(message: String) {
                _failure.value = message
            }
        })
    }

}