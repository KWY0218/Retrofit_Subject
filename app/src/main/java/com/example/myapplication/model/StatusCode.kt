package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class StatusCode (
     @SerializedName("statusCode")
     var statusCode:Int?
     )

data class Body(
     @SerializedName("version")
     var bodyInVersion:Long?,
     @SerializedName("force")
     var bodyInForce:String?
)
