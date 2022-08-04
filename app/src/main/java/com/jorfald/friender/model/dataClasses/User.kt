package com.jorfald.friender.model.dataClasses

import androidx.room.Entity
import androidx.room.PrimaryKey

    @Entity
    data class User(
        val email: String
)
