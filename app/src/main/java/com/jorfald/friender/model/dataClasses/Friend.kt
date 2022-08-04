package com.jorfald.friender.model.dataClasses

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
@Entity(tableName = "friends")
data class Friend(
    @PrimaryKey
    var id: Long,
    val first_name: String,
    val last_name: String,
    val email: String,
    val gender: String,
    val date_of_birth: String,


    @Embedded
    val employment: FriendEmployment?,

    @Embedded
    val address: FriendLocation,

    var avatar: String?
) : Parcelable
