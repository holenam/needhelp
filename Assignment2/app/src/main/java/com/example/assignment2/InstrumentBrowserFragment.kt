package com.example.assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class InstrumentBrowserFragment : Fragment() {
    private val instruments = InstrumentRepository.getInstruments()
    private var currentIndex = 0

    private lateinit var instrumentImage: ImageView
    private lateinit var instrumentName: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var instrumentPrice: TextView
    private lateinit var nextButton: Button
    private lateinit var borrowButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_instrument_browser, container, false)

        instrumentImage = view.findViewById(R.id.instrumentImage)
        instrumentName = view.findViewById(R.id.instrumentName)
        ratingBar = view.findViewById(R.id.ratingBar)
        instrumentPrice = view.findViewById(R.id.instrumentPrice)
        nextButton = view.findViewById(R.id.nextButton)
        borrowButton = view.findViewById(R.id.borrowButton)

        updateInstrument()

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % instruments.size
            updateInstrument()
        }

        borrowButton.setOnClickListener {
            val action = InstrumentBrowserFragmentDirections.actionInstrumentBrowserFragmentToBookingFragment(instruments[currentIndex])
            findNavController().navigate(action)
        }

        return view
    }

    private fun updateInstrument() {
        val instrument = instruments[currentIndex]
        instrumentImage.setImageResource(instrument.imageResId)
        instrumentName.text = instrument.name
        ratingBar.rating = instrument.rating
        instrumentPrice.text = "${instrument.pricePerMonth} credits/month"
    }
}
