package com.fabricio.travel.services

import com.fabricio.travel.commons.SuitCase
import com.google.gson.annotations.SerializedName

class BagResponse {

  /*  @SerializedName("errorImage")
    var imageError :String? = null
*/
    @SerializedName("suitcase")
    var suitcase: List<SuitCase>? = null
}