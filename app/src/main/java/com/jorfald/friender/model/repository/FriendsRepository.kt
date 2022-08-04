package com.jorfald.friender.model.repository

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.jorfald.friender.*
import com.jorfald.friender.Database.AppDatabase
import com.jorfald.friender.model.dataClasses.Friend
import com.jorfald.friender.model.dataClasses.FriendEmployment
import com.jorfald.friender.model.dataClasses.FriendLocation


class FriendsRepository {
    private val db = AppDatabase
    private val context = FrienderApplication().getContext()
    private val dao = db.getDatabase(context).getDao()
    private val dummyFriend = (Friend(404,"You are","offline.","USER","DOES","2020-12-12",
        FriendEmployment("Check your internet connection.","Or try again later"), FriendLocation("",""),"none"))

    private var queue: RequestQueue =
        Volley.newRequestQueue(FrienderApplication.application.applicationContext)

    fun fetchFriend(callback: (Friend) -> Unit) {
        val apiURL = "$BASE_URL_FRIENDS/random_user"
        val stringRequest = StringRequest(
            Request.Method.GET,
            apiURL,
            { friends ->
                val friend = Klaxon().parse<Friend>(friends)
                if (friend != null) {
                        callback(friend)
                } else {
                    callback(dummyFriend)
                }
            },
            { error ->
                Log.d("repository", "ERROR: API/CLIENT/SERVER ISSUE \n $error")
                callback(dummyFriend)
                }
        )
        queue.add(stringRequest)
    }

    fun saveFriend(friend: Friend) {

            dao.saveFriend(friend)

    }

    fun deleteAllFriends() {

        dao.deleteAllFriends()

    }

    fun getSavedFriends(callback: (List<Friend>) -> Unit) {

            callback(dao.getAll())

    }

    fun deleteFriend(friend: Friend) {

            dao.delete(friend.id)

    }


}