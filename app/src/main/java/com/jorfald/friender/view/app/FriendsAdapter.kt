package com.jorfald.friender.view.app

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.friender.model.dataClasses.Friend
import com.jorfald.friender.FrienderApplication
import com.jorfald.friender.viewModel.FriendsViewModel

class FriendsAdapter(
    var all: List<Friend> = listOf(),
    val crossClicked: ((Friend) -> Unit)? = null
) :
    RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {
    var viewModel = FriendsViewModel()

    inner class FriendsViewHolder(val viewMy: MyFriendsView) : RecyclerView.ViewHolder(viewMy)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val view = MyFriendsView(parent.context)

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return FriendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        var friend = all[position]
        var mutableFriends: MutableList<Friend> = all.toMutableList()
        FrienderApplication.application.applicationContext
        holder.viewMy.setData(all[position])
        holder.viewMy.setCrossListener {
            notifyItemRemoved(position)
            viewModel.removeFriend(friend)
            mutableFriends.removeAt(position)
            viewModel.friendRemoved(friend)
            updateData(
                mutableFriends
            )

        }

    }

    override fun getItemCount(): Int = all.size

    fun updateData(newList: List<Friend>) {
        all = newList
        notifyDataSetChanged()
    }

}
