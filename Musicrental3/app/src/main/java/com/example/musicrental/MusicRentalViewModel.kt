package com.example.musicrental

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MusicRentalViewModel : ViewModel() {

    private val _instruments = MutableLiveData(
        mutableListOf(
            Instrument("Guitar", R.drawable.guitar, 4.5f, listOf("Acoustic", "Electric"), 20, 5, null),
            Instrument("Piano", R.drawable.piano, 4.8f, listOf("Digital", "Grand"), 150, 3, null),
            Instrument("Drums", R.drawable.drum, 4.2f, listOf("Full Kit", "Electronic"), 35, 2, null)
        )
    )
    val instruments: LiveData<MutableList<Instrument>> = _instruments

    private val _currentInstrumentIndex = MutableLiveData(0)
    val currentInstrumentIndex: LiveData<Int> = _currentInstrumentIndex

    private val _userCredits = MutableLiveData(100)
    val userCredits: LiveData<Int> = _userCredits

    fun nextInstrument() {
        _currentInstrumentIndex.value = (_currentInstrumentIndex.value?.plus(1) ?: 0) % _instruments.value!!.size
    }

    fun prevInstrument() {
        _currentInstrumentIndex.value = (_currentInstrumentIndex.value?.minus(1)?.takeIf { it >= 0 } ?: _instruments.value!!.size - 1)
    }

    fun updateInstruments(updatedList: MutableList<Instrument>?) {
        _instruments.value = updatedList
    }

    fun borrowInstrument(renterName: String): Boolean {
        val index = _currentInstrumentIndex.value ?: 0
        val instrument = _instruments.value?.get(index) ?: return false

        return if (renterName.isNotEmpty() && instrument.stock > 0 && (_userCredits.value ?: 0) >= instrument.price) {
            instrument.stock -= 1
            instrument.rentedBy = renterName
            _userCredits.value = (_userCredits.value ?: 0) - instrument.price
            true
        } else {
            false
        }
    }
}
