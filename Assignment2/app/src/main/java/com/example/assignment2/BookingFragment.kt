package com.example.assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class BookingFragment : Fragment() {
    private lateinit var bookingText: TextView
    private lateinit var confirmBookingButton: Button
    private lateinit var instrumentImage: ImageView
    private val args: BookingFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_booking, container, false)

        bookingText = view.findViewById(R.id.bookingText)
        confirmBookingButton = view.findViewById(R.id.confirmBookingButton)
        instrumentImage = view.findViewById(R.id.instrumentImage)

        instrumentImage.setImageResource(args.selectedInstrument.imageResId)
        bookingText.text = "${args.selectedInstrument.name} - ${args.selectedInstrument.pricePerMonth} credits/month"

        confirmBookingButton.setOnClickListener {
            SnackbarManager.showSnackbar(it, "${args.selectedInstrument.name} booked successfully!")
        }

        return view
    }
}
