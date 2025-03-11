package com.example.assignment2

import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackbarManager {
    fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}
