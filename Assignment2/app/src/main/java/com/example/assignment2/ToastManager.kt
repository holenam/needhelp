package com.example.assignment2

import android.content.Context
import android.widget.Toast

object ToastManager {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

