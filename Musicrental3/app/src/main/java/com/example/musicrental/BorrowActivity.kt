package com.example.musicrental

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class BorrowActivity : AppCompatActivity() {

    private lateinit var instrument: Instrument
    private lateinit var renterNameEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var descriptionText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrow)

        // Retrieve Instrument object
        instrument = intent.getParcelableExtra("instrument") ?: return

        // Initialize Views
        val instrumentImage = findViewById<ImageView>(R.id.borrowImage)
        val instrumentName = findViewById<TextView>(R.id.borrowName)
        val priceText = findViewById<TextView>(R.id.priceText)
        val creditText = findViewById<TextView>(R.id.creditText)
        renterNameEditText = findViewById(R.id.renterName)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)
        radioGroup = findViewById(R.id.attributeRadioGroup)
        descriptionText = findViewById(R.id.borrowDescription)

        // Set Instrument Data
        instrumentImage.setImageResource(instrument.imageResId)
        instrumentName.text = instrument.name
        priceText.text = "Price: ${instrument.price} Credits"
        creditText.text = "Available Credits: 100"
        descriptionText.text = getInstrumentDescription(instrument.name)

        // Populate RadioGroup dynamically based on available attributes
        populateRadioButtons(instrument.attributes)

        // Enable Save Button only when both name and an attribute are selected
        renterNameEditText.addTextChangedListener {
            saveButton.isEnabled = isFormValid()
        }

        radioGroup.setOnCheckedChangeListener { _, _ ->
            saveButton.isEnabled = isFormValid()
        }

        // Save Button Click Listener
        saveButton.setOnClickListener {
            val renterName = renterNameEditText.text.toString()
            val selectedAttribute = getSelectedAttribute()

            if (renterName.isNotEmpty() && selectedAttribute != null) {
                val updatedInstrument = instrument.copy(
                    stock = instrument.stock - 1,
                    rentedBy = renterName
                )

                val resultIntent = Intent()
                resultIntent.putExtra("updatedInstrument", updatedInstrument)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Please enter your name and select an attribute!", Toast.LENGTH_SHORT).show()
            }
        }

        // Cancel Button Click Listener
        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun populateRadioButtons(attributes: List<String>) {
        radioGroup.removeAllViews()
        for (attribute in attributes) {
            val radioButton = RadioButton(this)
            radioButton.text = attribute
            radioGroup.addView(radioButton)
        }
    }

    private fun getSelectedAttribute(): String? {
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        return if (selectedRadioButtonId != -1) {
            findViewById<RadioButton>(selectedRadioButtonId).text.toString()
        } else {
            null
        }
    }

    private fun isFormValid(): Boolean {
        return renterNameEditText.text.toString().isNotEmpty() && radioGroup.checkedRadioButtonId != -1
    }

    private fun getInstrumentDescription(name: String): String {
        return when (name) {
            "Guitar" -> "A versatile string instrument suitable for various music styles."
            "Piano" -> "A classic instrument with digital and grand variations."
            "Drums" -> "A percussion instrument available in full kit and electronic variations."
            else -> "A high-quality instrument for your musical needs."
        }
    }
}
