package com.jorfald.friender.view.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.jorfald.friender.R
import com.jorfald.friender.model.dataClasses.Friend
import com.jorfald.friender.utils.Utils
import com.squareup.picasso.Picasso

class MyFriendsView(context: Context) : FrameLayout(context) {
    lateinit var friendName: TextView
    lateinit var friendPlace: TextView
    lateinit var friendPicture: ImageView
    lateinit var friendHolder: ConstraintLayout

    private val view: View = LayoutInflater.from(context).inflate(R.layout.friends_view, this)

    fun setData(friend: Friend) {
        friendName = view.findViewById(R.id.friend_name_text)
        friendPlace = view.findViewById(R.id.friend_place_text)
        friendPicture = view.findViewById(R.id.friend_profile_picture)
        friendHolder = view.findViewById(R.id.friend_click_wrapper)

        // Fylle Views med venn-info.

        friendName.text =
            Utils.getFullName(friend.first_name, friend.last_name) + " (" + Utils.getAge(
                2022,
                friend.date_of_birth
            ) + ")"

       friendPlace.text =
            Utils.getPlaceText(friend.address)
       friendHolder.setOnClickListener {
           val direction =
               com.jorfald.friender.view.app.FriendsListFragmentDirections.actionFriendsListFragmentToFriendInfo2(
                   friend
               )
           findNavController().navigate(direction)
        }
       Picasso.get().load(friend.avatar).into(friendPicture)

    }

    fun setCrossListener(click: () -> Unit) {
        view.findViewById<ImageView>(R.id.friend_cross_button).setOnClickListener {
            click()
        }
    }
}
