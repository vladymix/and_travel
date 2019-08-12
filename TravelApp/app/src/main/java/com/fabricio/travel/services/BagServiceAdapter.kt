package com.fabricio.travel.services

import com.fabricio.travel.commons.Constants
import com.fabricio.travel.deserializer.BagDeserializer
import com.google.gson.GsonBuilder
import retrofit.RestAdapter
import retrofit.converter.GsonConverter
import rx.Observable


class BagServiceAdapter {

    companion object {
        var API_SERVICE: BagService? = null

        fun getApiService(): BagService {
            if (API_SERVICE == null) {
                val adapter = RestAdapter.Builder()
                    .setEndpoint(Constants.URL)
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .setConverter(buildGsonConverter())
                    .build()

                API_SERVICE = adapter.create(BagService::class.java)
            }
            return API_SERVICE!!
        }


        private fun buildGsonConverter(): GsonConverter {
            val gsonConf = GsonBuilder()
                .registerTypeAdapter(BagResponse::class.java, BagDeserializer())
                .create()

            return GsonConverter(gsonConf)
        }


        fun getBagList(): Observable<BagResponse> {
            return getApiService().getItems()
        }

    }


}