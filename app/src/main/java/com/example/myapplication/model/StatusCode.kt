package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class StatusCode (
     // "statusCode": 200
     @SerializedName("statusCode")
     var statusCode:Int?,
     // "body": {"version": 1.5, "force": true}
     @SerializedName("body")
     var body:Body = Body()
     )

class Body{
     @SerializedName("version")
     var bodyInVersion:Float? = null
     @SerializedName("force")
     var bodyInForce:Boolean? = null
}


