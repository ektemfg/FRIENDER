package com.template.androidtemplate.utils

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.friender.R


class FriendsDataObserver(view: View, recview: RecyclerView, emptyView: View): RecyclerView.AdapterDataObserver() {

    private var noFriendsView: View = emptyView
    private var friendsRecyclerView: RecyclerView = recview
    private var yourFriends: TextView = view.findViewById(R.id.friends_list_title)
    private var backButton: Button = view.findViewById(R.id.friends_back_button)
    private var refresh: Button = view.findViewById(R.id.friends_list_refresh)
    private var goFind: Button = view.findViewById(R.id.go_find_friends)



    private fun gotFriends() {
        if (friendsRecyclerView.adapter != null) {
            val showNoFriends = friendsRecyclerView.adapter!!.itemCount == 0
            noFriendsView.visibility = if (showNoFriends) View.VISIBLE else View.GONE
            friendsRecyclerView.visibility = if (showNoFriends) View.GONE else View.VISIBLE
            yourFriends.visibility = if (showNoFriends) View.GONE else View.VISIBLE
            backButton.visibility = if (showNoFriends) View.GONE else View.VISIBLE
            refresh.visibility = if (showNoFriends) View.GONE else View.VISIBLE
            goFind.visibility = if (showNoFriends) View.VISIBLE else View.GONE
            goFind.setOnClickListener{
                backButton.performClick()
            }
        }
    }

    override fun onChanged() {
        super.onChanged()
        gotFriends()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        super.onItemRangeChanged(positionStart, itemCount)
    }

}