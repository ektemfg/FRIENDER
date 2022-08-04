package com.jorfald.friender.viewModel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorfald.friender.*
import com.jorfald.friender.Database.AppDatabase
import com.jorfald.friender.FrienderApplication.Companion.application
import com.jorfald.friender.model.dataClasses.Friend
import com.jorfald.friender.model.repository.FriendsRepository
import com.jorfald.friender.utils.Utils
import com.jorfald.friender.view.app.FriendsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit


class FriendsViewModel : ViewModel() {

    val fetchedFriend: MutableLiveData<Friend> = MutableLiveData()
    val savedFriends: MutableLiveData<List<Friend>> = MutableLiveData()
    private val friendsRepo = FriendsRepository()
    var context = FrienderApplication.application.applicationContext
    val view = FriendsFragment()

    val dao = AppDatabase.getDatabase(application).getDao()

    // Repo henter venn fra API og lagrer i LiveData her.
    fun initializeRepo() {
        CoroutineScope(Dispatchers.IO).launch {
            friendsRepo.fetchFriend {
                fetchedFriend.postValue(it)
            }
        }.start()
    }

    // Henter allerede lagra venner fra DB, oppdaterer lista her.
    fun fetchFriendsValues() {
        CoroutineScope(Dispatchers.IO).launch {
            friendsRepo.getSavedFriends { friends ->
                savedFriends.postValue(friends)
            }
        }.start()
    }

    // Lagrer vennen i databasen via repository fun, som snakker til DAO.
    fun saveFriend(friend: Friend, fetchedPic: String) {
        CoroutineScope(Dispatchers.IO).launch {
            friend.avatar = fetchedPic
            friendsRepo.saveFriend(friend)
        }.start()
    }
    // Fjerner vennen i databasen via repository fun, som snakker til DAO.
    fun removeFriend(friend: Friend) {
        CoroutineScope(Dispatchers.IO).launch {
            friendsRepo.deleteFriend(friend)
        }.start()
    }
    fun friendAdded(friend: Friend) {
        val message = Toast.makeText(
            context,
            "Added ${friend.first_name} ${friend.last_name} as a friend!",
            Toast.LENGTH_SHORT
        )
        message.show()
    }

    fun friendRemoved(friend: Friend) {
        Toast.makeText(
            context,
            "You are not longer friends with ${friend.first_name} ${friend.last_name}.",
            Toast.LENGTH_SHORT
        ).show()
    }


    fun toast(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun friendBackground(friend: Friend, background: View) {
        when {
            Utils.isMale(friend.gender) == "male" -> {
                background.setBackgroundResource(R.drawable.friend_info_men)
            }
            Utils.isMale(friend.gender) == "female" -> {
                background.setBackgroundResource(R.drawable.friend_info_women)
            }
            else -> {
                background.setBackgroundColor(background.resources.getColor(R.color.white))
            }
        }
    }


    fun celebrate(confetti : KonfettiView) {
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        confetti.start(party)

    }




}






