package com.fabricio.travel.services

import com.fabricio.travel.commons.Constants
import retrofit.http.GET
import rx.Observable

interface BagService {

    @GET(Constants.API)
    fun getItems() : Observable<BagResponse>
}