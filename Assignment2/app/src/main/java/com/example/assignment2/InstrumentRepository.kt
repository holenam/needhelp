package com.example.assignment2

object InstrumentRepository {
    private val instruments = listOf(
        Instrument("Electric Guitar", R.drawable.guitar, 4.5f, 50, listOf("Rock", "Metal")),
        Instrument("Keyboard", R.drawable.keyboard, 4.2f, 40, listOf("Jazz", "Pop")),
        Instrument("Drum Set", R.drawable.drums, 4.7f, 60, listOf("Rock", "Funk")),
        Instrument("Violin", R.drawable.violin, 4.6f, 45, listOf("Classical", "Folk"))
    )

    fun getInstruments(): List<Instrument> = instruments
}
