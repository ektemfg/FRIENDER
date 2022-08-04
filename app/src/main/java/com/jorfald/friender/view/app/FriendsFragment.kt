package com.jorfald.friender.view.app

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import com.jorfald.friender.*
import com.jorfald.friender.model.dataClasses.Friend
import com.jorfald.friender.viewModel.FriendsViewModel
import com.jorfald.friender.utils.Utils
import com.jorfald.friender.utils.NetworkChecker
import com.jorfald.friender.view.login.LoginViewModel
import com.squareup.picasso.Picasso
import nl.dionsegijn.konfetti.xml.KonfettiView

class FriendsFragment:Fragment() {
    private val viewModelFriends: FriendsViewModel by viewModels()


    // Views
    private lateinit var loader: LottieAnimationView
    private lateinit var ageField: TextView
    private lateinit var nameField: TextView
    private lateinit var genderIconField: ImageView
    private lateinit var employmentTextField: TextView
    private lateinit var placeTextField: TextView
    private lateinit var check_button: ImageView
    private lateinit var myFriendsButton: Button
    private lateinit var cross_button: ImageView
    private lateinit var logout: Button
    private lateinit var friendCard: CardView
    private lateinit var profilePictureField: ImageView
    private lateinit var fetchedPic: String
    // Party Party! ;-)
    private lateinit var celebrate: KonfettiView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friend_search_views, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initiere viewsa, koble variabler til Views.
        celebrate = view.findViewById(R.id.viewCelebrate)
        loader = view.findViewById(R.id.loader)
        loader.visibility = View.VISIBLE
        ageField = view.findViewById(R.id.age_text)
        nameField = view.findViewById(R.id.name_text)
        genderIconField = view.findViewById(R.id.gender_icon)
        employmentTextField = view.findViewById(R.id.employment_text)
        employmentTextField.visibility = View.GONE
        placeTextField = view.findViewById(R.id.place_text)
        check_button = view.findViewById(R.id.check_button)
        cross_button = view.findViewById(R.id.cross_button)
        myFriendsButton = view.findViewById(R.id.my_friends_button)
        logout = view.findViewById(R.id.friends_logout)
        friendCard = view.findViewById(R.id.person_card)
        profilePictureField = view.findViewById(R.id.profile_image)



        profilePictureField.visibility = View.GONE

        // Finne en person, fylle views, gi knappene funksjon, gå til neste person.

        viewModelFriends.fetchedFriend.observe(viewLifecycleOwner) {
            fillData(it)
            bindButtons(it)
        }

            nextFriend()


    }




    private fun bindButtons(friend: Friend) {
        // Binde alle knappene som tidligere var i MainActivity, og nå i sitt eget fragment.
        // Knappene gjør blandtannet kall til viweModel, viewmodel til repo -> repo snakker også til RoomDB via DAO.
        check_button.setOnClickListener() {
                viewModelFriends.saveFriend(friend, fetchedPic)
            viewModelFriends.friendAdded(friend)
            nextFriend()
            viewModelFriends.celebrate(celebrate)

        }
        cross_button.setOnClickListener() {
            nextFriend()

        }
        myFriendsButton.setOnClickListener() {

           findNavController().navigate(R.id.action_friendsFragment2_to_friendsListFragment)
        }
        logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_friendsFragment_to_loginFragment)
            viewModelFriends.toast("Bye!")
        }

        loader.visibility = View.GONE
    }
// Neste venn blir vist, ved å instruere viewmodel å hente igjen fra repo. Føler det er noe extra kode her men.
    private fun nextFriend() {

    viewModelFriends.initializeRepo()




    }
        // Her fylles vi viewsa, ved å ta imot Venn-Info fra observeren, som sjekker om listen har endra seg via
    // viewModel kall som igjen henter data fra REPOSITORY.
    fun fillData(friend: Friend) {
        nameField.text = Utils.getFullName(friend.first_name, friend.last_name)
        placeTextField.text = Utils.getPlaceText(friend.address)
        ageField.text = Utils.getAge(2022, friend.date_of_birth)
        ageField.setTextColor(
            Utils.getGenderColor(ageField.context, friend.gender).toString().toInt()
        )
        genderIconField.setImageDrawable(
            Utils.getGenderIcon(genderIconField.context, friend.gender)
        )
        employmentTextField.text = Utils.getEmploymentText(friend.employment)
        placeTextField.text = Utils.getPlaceText(friend.address)
            val context = FrienderApplication.application.applicationContext
            if (NetworkChecker.isOnline(context)) {
                cross_button.foregroundGravity = Gravity.NO_GRAVITY
                check_button.visibility = View.VISIBLE
                cross_button.setImageResource(R.drawable.ic_twotone_circle_cross)
                ageField.visibility = View.VISIBLE
                genderIconField.visibility = View.VISIBLE
                employmentTextField.visibility = View.VISIBLE
               fetchedPic = Utils.getProfilePictureUrl(friend)
                Picasso.get().load(fetchedPic).into(profilePictureField)
                myFriendsButton.visibility = View.VISIBLE
                logout.visibility = View.VISIBLE



            } else {
                viewModelFriends.toast("You are offline...")
                ageField.visibility = View.GONE
                genderIconField.visibility = View.GONE
                employmentTextField.visibility = View.GONE
               profilePictureField.setImageResource(R.drawable.error)
                check_button.visibility = View.GONE
                cross_button.foregroundGravity = Gravity.CENTER
                myFriendsButton.visibility = View.GONE
                logout.visibility = View.GONE
                cross_button.setImageResource(R.drawable.ic_refresh)
                cross_button.setOnClickListener() {
                    viewModelFriends.toast("You are offline")
                }
            }
            profilePictureField.visibility = View.VISIBLE



    }


}
