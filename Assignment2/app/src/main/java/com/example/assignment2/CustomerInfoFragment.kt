package com.example.assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class CustomerInfoFragment : Fragment() {

    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_customer_info, container, false)

        nameInput = view.findViewById(R.id.editTextName)
        emailInput = view.findViewById(R.id.editTextEmail)
        phoneInput = view.findViewById(R.id.editTextPhone)
        saveButton = view.findViewById(R.id.buttonSave)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()

            if (name.isEmpty()) {
                nameInput.error = "Name is required!"
                return@setOnClickListener
            }

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.error = "Valid email is required!"
                return@setOnClickListener
            }

            if (phone.isEmpty() || phone.length < 8) {
                phoneInput.error = "Valid phone number is required!"
                return@setOnClickListener
            }

            Toast.makeText(requireContext(), "Details Saved!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
