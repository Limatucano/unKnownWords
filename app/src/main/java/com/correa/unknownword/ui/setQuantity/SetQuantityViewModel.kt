package com.correa.unknownword.ui.setQuantity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SetQuantityViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData(SetQuantityState())
    val state: LiveData<SetQuantityState> = _state

    fun onTextChanged(text: String) {
        _state.value = _state.value?.copy(quantity = text)
    }

    fun validateField(text: String?){
        _state.value = _state.value?.copy(
            isError = text?.toIntOrNull() == null || text.toInt() !in 1..5
        )
    }
}