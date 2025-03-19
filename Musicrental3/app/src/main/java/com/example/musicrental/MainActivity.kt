package com.example.musicrental

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private val viewModel: MusicRentalViewModel by viewModels()

    // Activity Result Launcher (New API)
    private val borrowActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val updatedInstrument = result.data?.getParcelableExtra("updatedInstrument", Instrument::class.java)
            updatedInstrument?.let { newInstrument ->
                val updatedList = viewModel.instruments.value?.toMutableList()
                val index = viewModel.currentInstrumentIndex.value ?: 0
                updatedList?.set(index, newInstrument)
                viewModel.updateInstruments(updatedList)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val instrumentImage = findViewById<ImageView>(R.id.instrumentImage)
        val instrumentName = findViewById<TextView>(R.id.instrumentName)
        val priceText = findViewById<TextView>(R.id.priceText)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val stockText = findViewById<TextView>(R.id.stockText)
        val rentedByText = findViewById<TextView>(R.id.rentedByText)
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
        val borrowButton = findViewById<Button>(R.id.borrowButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val prevButton = findViewById<Button>(R.id.prevButton)
        val themeSwitch = findViewById<SwitchCompat>(R.id.themeSwitch) // Fixed reference

        // Dark Mode Toggle
        themeSwitch.isChecked = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            recreate()
        }

        fun updateUI() {
            val index = viewModel.currentInstrumentIndex.value ?: 0
            val instrument = viewModel.instruments.value?.get(index)

            instrument?.let {
                instrumentImage.setImageResource(it.imageResId)
                instrumentName.text = it.name
                priceText.text = getString(R.string.price_format, it.price)
                ratingBar.rating = it.rating
                stockText.text = getString(R.string.stock_format, it.stock)
                rentedByText.text = getString(R.string.rented_by_format, it.rentedBy ?: "Available")

                chipGroup.removeAllViews()
                it.attributes.forEach { attribute ->
                    val chip = Chip(this)
                    chip.text = attribute
                    chipGroup.addView(chip)
                }

                borrowButton.isEnabled = it.stock > 0 && (viewModel.userCredits.value ?: 0) >= it.price
            }
        }

        // Observe LiveData for automatic UI updates
        viewModel.instruments.observe(this) { updateUI() }
        viewModel.currentInstrumentIndex.observe(this) { updateUI() }

        // Borrow button: send item data to BorrowActivity using the new API
        borrowButton.setOnClickListener {
            val index = viewModel.currentInstrumentIndex.value ?: 0
            val instrument = viewModel.instruments.value!![index]

            val intent = Intent(this, BorrowActivity::class.java)
            intent.putExtra("instrument", instrument)
            borrowActivityLauncher.launch(intent) // Using new API
        }

        // Next and Previous Buttons
        nextButton.setOnClickListener { viewModel.nextInstrument() }
        prevButton.setOnClickListener { viewModel.prevInstrument() }
    }
}
