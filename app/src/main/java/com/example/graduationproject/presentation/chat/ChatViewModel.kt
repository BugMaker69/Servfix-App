package com.example.graduationproject.presentation.chat

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.Chat
import com.example.graduationproject.data.Rate
import com.example.graduationproject.data.SendChatMessage
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
var deletedMessageId by mutableStateOf(0)
    var showAlert by mutableStateOf(false)
    var terminateAlert  by mutableStateOf(false)
    val chat = MutableStateFlow(emptyList<Chat>())
    var retrievedId by mutableStateOf(0)
    var retrievedPhone by mutableStateOf("")
    var retrievedName by mutableStateOf("")
    var retrievedImage by mutableStateOf("")
    var rating by mutableStateOf(0)
    var terminate_id by mutableStateOf(0)
    init {
        retrievedPhone=savedStateHandle.get<String>("phone")!!

        retrievedId= savedStateHandle.get<Int>("chatId")!!
        retrievedName=savedStateHandle.get<String>("name")!!
        retrievedImage=savedStateHandle.get<String>("image")!!
         terminate_id=savedStateHandle.get<Int>("terminate_id")!!
        Log.d("pop", "retrievedId:$retrievedId ")
            getData(retrievedId)

        Log.d("oop", "${chat.value.size}: ")
    }
    fun terminateChat(){

        viewModelScope.launch (Dispatchers.IO){
            chatRepository.determinateChat(terminate_id)
        }
    }
    fun addMessage(id:Int,message: String){
        viewModelScope.launch(Dispatchers.IO){
            val mesg= SendChatMessage(message)
            chatRepository.addMessage(id, sendChatMessage = mesg)
        }

    }
    fun addReview(){
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("ool", "addReview: $retrievedId and $rating")
            val rate = Rate(rating)
            chatRepository.addReview(retrievedId, rate = rate)



        }
    }
    fun deleteMessage(){
        viewModelScope.launch(Dispatchers.IO) {
            chatRepository.deleteMessage(deletedMessageId)
        }

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
                delay(3000)
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