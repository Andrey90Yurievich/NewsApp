package ru.ayuandrey.neewsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ayuandrey.neewsapp.domain.usercase.app_entry.SaveAppEntry
import javax.inject.Inject


@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveAppEntry: SaveAppEntry
) : ViewModel() {

    fun onEvent(event: OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry ->{
                saveUserEntry()
            }
        }
    }

    private fun saveUserEntry() {
        viewModelScope.launch {
            saveAppEntry()
        }
    }

}