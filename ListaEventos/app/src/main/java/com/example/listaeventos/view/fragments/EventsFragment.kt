package com.example.listaeventos.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listaeventos.databinding.FragmentEventsBinding
import com.example.listaeventos.service.listener.EventListener
import com.example.listaeventos.service.model.EventsModel
import com.example.listaeventos.view.adapter.EventsAdapter
import com.example.listaeventos.viewmodel.EventsViewModel

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EventsViewModel
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[EventsViewModel::class.java]
        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        viewModel.getEvents()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setEventsList(list: List<EventsModel>) {
        adapter = EventsAdapter(list)
        binding.rvEvents.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.rvEvents.adapter = adapter
        attachListener()
    }

    private fun observe() {
        viewModel.events.observe(viewLifecycleOwner, {
            setEventsList(it)
        })

        viewModel.failure.observe(viewLifecycleOwner, {
            Toast.makeText(activity?.applicationContext, it, Toast.LENGTH_SHORT).show()
        })
    }

    fun attachListener(){
        val listener = object : EventListener {
            override fun onClickEvent(event: EventsModel) {
                val action = EventsFragmentDirections.actionEventsFragmentToEventDetailsFragment(event)
                findNavController().navigate(action)
            }
        }
        adapter.attachListener(listener)
    }
}
