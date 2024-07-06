package com.kursatkumsuz.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _messageState = MutableStateFlow(savedStateHandle.toRoute<Screen.DetailScreen>().message)
    val messageState: StateFlow<String?> = _messageState
}