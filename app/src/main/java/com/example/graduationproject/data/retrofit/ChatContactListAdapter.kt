package com.example.graduationproject.data.retrofit

import com.example.graduationproject.data.ChatContactList
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class ChatContactListAdapter : JsonDeserializer<ChatContactList> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ChatContactList {
        val jsonObject = json.asJsonObject

        return when {
            jsonObject.has("accepted_users") -> context.deserialize<ChatContactList.UserResponse>(json, ChatContactList.UserResponse::class.java)
            jsonObject.has("accepted_providers") -> context.deserialize<ChatContactList.ProviderResponse>(json, ChatContactList.ProviderResponse::class.java)
            else -> throw JsonParseException("Not a valid ChatContactList")
        }
    }
}
