package com.jorfald.friender.model.dataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FriendLocation(
    val city: String,
    val country: String
):Parcelable