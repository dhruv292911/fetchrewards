package com.example.fetchrewards

import com.google.gson.annotations.SerializedName

data class Rewards(
    @SerializedName("id") val id: Int,
    @SerializedName("listId") val listId: Int?,
    @SerializedName("name") val name: String
)