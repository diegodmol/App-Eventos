package com.example.listaeventos.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.listaeventos.R
import com.example.listaeventos.service.utils.Extensions.toTimeDateString
import com.example.listaeventos.databinding.FragmentEventDetailsBinding
import com.example.listaeventos.service.utils.Extensions.bindPhoto
import com.example.listaeventos.service.utils.Extensions.priceWithDecimal
import com.example.listaeventos.viewmodel.EventsViewModel

class EventDetailsFragment : Fragment() {

    private var _binding: FragmentEventDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EventsViewModel
    private val args: EventDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[EventsViewModel::class.java]
        _binding = FragmentEventDetailsBinding.inflate(inflater, container, false)

        setView()
        setListeners()
        observe()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setView(){
        binding.itemTitle.text = args.eventModel?.title
        binding.itemDate.text = args.eventModel?.date?.toTimeDateString()
        binding.itemPrice.text = args.eventModel?.price?.priceWithDecimal()
        args.eventModel?.image?.let { bindPhoto(it, binding.itemImage) }
    }

    private fun setListeners(){
        binding.btnCheckIn.setOnClickListener {
            viewModel.postCheckInEvents(args.eventModel!!.id,"Márcio Amaral", "marcioamaral@gmail.com")
        }
    }

    private fun observe() {
        viewModel.eventsCheckIn.observe(viewLifecycleOwner, {
            Toast.makeText(activity?.applicationContext, R.string.check_in_success, Toast.LENGTH_SHORT).show()
        })

        viewModel.failure.observe(viewLifecycleOwner, {
            Toast.makeText(activity?.applicationContext, it, Toast.LENGTH_SHORT).show()
        })
    }
}