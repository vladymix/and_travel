package com.fabricio.travel.deserializer

import com.fabricio.travel.services.BagResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import com.google.gson.Gson



class BagDeserializer: JsonDeserializer<BagResponse> {


    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): BagResponse {

        val gson = Gson()

        val response = gson.fromJson(json, BagResponse::class.java)

        return response
    }
}