package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class LectureResult(
    @SerializedName("result")
    var resultList:List<Lecture>? = null
)

class Lecture{
    @SerializedName("id")
    var id:Int? = null
    @SerializedName("name")
    var subjectName:String? = null
    @SerializedName("professor")
    var professor:String? = null
}