package com.jorfald.friender.model.dataClasses


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FriendEmployment(
    val title: String,
    val key_skill: String
): Parcelable