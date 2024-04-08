package com.example.custom_networking

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoroutineExecutor {
    fun execute(runnable: suspend () -> Unit) {
        // Create a new coroutine scope
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            runnable()
        }
    }
}
