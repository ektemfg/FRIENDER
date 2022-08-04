package com.jorfald.friender.view.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jorfald.friender.R
import com.jorfald.friender.utils.Utils
import com.jorfald.friender.viewModel.FriendsViewModel
import com.squareup.picasso.Picasso

class FriendsInfoFragment : Fragment() {
    val args by navArgs<com.jorfald.friender.view.app.FriendsInfoFragmentArgs>()
    private lateinit var layout: ConstraintLayout
    private lateinit var avatar: ImageView
    private lateinit var name: TextView
    private lateinit var  gender: TextView
    private lateinit var   age: TextView
    private lateinit var   place: TextView
    private lateinit var  job: TextView
    private lateinit var  email: TextView
    private lateinit var  backButton: Button
    private lateinit var removeButton: Button
    private val viewModel: FriendsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friend_info, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         // Define views
    layout = view.findViewById(R.id.friend_info_layout)
        avatar = view.findViewById(R.id.friend_info_avatar)
        name = view.findViewById(R.id.friend_info_name)
        gender = view.findViewById(R.id.friend_info_gender)
        age = view.findViewById(R.id.friend_info_age)
        place = view.findViewById(R.id.friend_info_location)
        job = view.findViewById(R.id.friend_info_job)
        email = view.findViewById(R.id.friend_info_email)
        backButton = view.findViewById(R.id.friend_info_backbutton)
        removeButton = view.findViewById(R.id.friend_info_remove)
        super.onViewCreated(view, savedInstanceState)

        // Get argument, give views value
        var friend = args.sentFriend
        viewModel.friendBackground(friend, layout)
        Picasso.get().load(friend.avatar).into(avatar)
        name.text = Utils.getFullName(friend.first_name, friend.last_name)
        gender.text = "Gender: " + friend.gender
        age.text = "Age: " + Utils.getAge(2022, friend.date_of_birth) + " years old."
        place.text = Utils.getPlaceText(friend.address)
        job.text = Utils.getEmploymentText(friend.employment)
        email.text = "E-mail:\n" + friend.email
        backButton.setOnClickListener {
            findNavController().navigate(com.jorfald.friender.view.app.FriendsInfoFragmentDirections.actionFriendInfoToFriendsListFragment2())
        }
        removeButton.setOnClickListener{
            viewModel.removeFriend(friend)
            findNavController().navigate(com.jorfald.friender.view.app.FriendsInfoFragmentDirections.actionFriendInfoToFriendsListFragment2())
        }







    }
}