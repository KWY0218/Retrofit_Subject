package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class LectureDetails(
    @SerializedName("name")
    var lectureDetailsName: String? = null
)