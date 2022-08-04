@file:Suppress("RedundantIf", "LiftReturnOrAssignment", "MemberVisibilityCanBePrivate")

package com.jorfald.friender.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.jorfald.friender.*
import com.jorfald.friender.model.dataClasses.Friend
import com.jorfald.friender.model.dataClasses.FriendEmployment
import com.jorfald.friender.model.dataClasses.FriendLocation

object Utils {
    fun getAge(thisYear: Int, dateOfBirth: String): String {
        return try {
            val year = dateOfBirth.substring(0, 4).toInt()
            (thisYear - year).toString()
        } catch (e: Exception) {
            "Invalid Age Request"
        }
    }

    fun getFullName(firstName: String, lastName: String): String {
        return "$firstName $lastName"
    }

    fun isMale(gender: String): String {
        when (gender.lowercase()) {
            "male" -> {
                return "male"
            }
            "female" -> {
                return "female"
            }
        "bigender" -> {
            return "male"
        }
        "genderfluid" -> {
            return "else"
        }
        "non-binary" -> {
            return "else"
        }
        "polygender" -> {
            return "male"
        }
        "agender" -> {
            return "else"
        }
            else -> {
                return "else"
            }
        }
    }


    fun getEmploymentText(employment: FriendEmployment?): String {
        return if (employment == null) {
            "Unemployed"
        } else {
            "Role: ${employment.title}\nSkill: ${employment.key_skill}"
        }
    }

    fun getPlaceText(address: FriendLocation): String {
        return "${address.city}, ${address.country}"
    }

    fun getProfilePictureUrl(friend: Friend): String {
        val number = (0..99).random()
        val isMale = isMale(friend.gender)

        return "https://randomuser.me/api/portraits/${if (isMale=="male") "men" else "women"}/$number.jpg"
    }

    fun getGenderIcon(context: Context, gender: String): Drawable? {
        when {
            isMale(gender) == "male" -> {

                return  ContextCompat.getDrawable(context, R.drawable.ic_male)
            }
            isMale(gender) == "female" -> {
                return ContextCompat.getDrawable(context, R.drawable.ic_female)
            }
            else -> {
                return ContextCompat.getDrawable(context, R.drawable.other)
            }
        }
    }

    fun getGenderColor(context: Context, gender: String): Int {
        when {
            isMale(gender) == "male" -> {
                return ContextCompat.getColor(context, R.color.blue)
            }
            isMale(gender) == "female" -> {
                return ContextCompat.getColor(context, R.color.pink)
            }
            else -> {
                return ContextCompat.getColor(context, R.color.purple_700)
            }
        }
    }





}