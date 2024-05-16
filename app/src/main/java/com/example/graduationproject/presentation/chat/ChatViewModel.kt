package com.example.graduationproject.presentation.chat

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.Chat
import com.example.graduationproject.data.repositories.ChatRepository
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.utils.DataStoreToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(val dataStoreToken: DataStoreToken,val apiService: ApiService,val savedStateHandle: SavedStateHandle,val chatRepository: ChatRepository) : ViewModel() {

    val chat = MutableStateFlow(emptyList<Chat>())
    var retrievedId by mutableStateOf(0)
    init {

        retrievedId= savedStateHandle.get<Int>("chatId")!!
        Log.d("pop", "retrievedId:$retrievedId ")
            getData(retrievedId)

        Log.d("oop", "${chat.value.size}: ")
    }
    fun getData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                Log.d("donee", "getData:entered the loop ")
                chatRepository.getChat(id).collect { tempList ->
                    tempList.forEach {
                        it.timestamp = convertTimestampTo12HourFormat(it.timestamp)
                    }
                    withContext(Dispatchers.Main) {
                        chat.value = tempList.reversed()
                        Log.d("donee", "getData: data updated")
                    }
                }
                delay(5000)
                Log.d("donee", "will fetch again")

            }
        }
    }


    fun convertTimestampTo12HourFormat(timestamp: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val date = sdf.parse(timestamp)

        val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()

        val formattedDate = outputFormat.format(date)

        val calendar = Calendar.getInstance()
        calendar.time = date
        val dateYear = calendar.get(Calendar.YEAR)
        val dateDayOfYear = calendar.get(Calendar.DAY_OF_YEAR)

        calendar.timeInMillis = System.currentTimeMillis()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR)

        return when {
            dateYear == currentYear && dateDayOfYear == currentDayOfYear -> "Today $formattedDate"
            dateYear == currentYear && dateDayOfYear == currentDayOfYear - 1 -> "Yesterday $formattedDate"
            else -> "later $formattedDate"
        }
    }





}