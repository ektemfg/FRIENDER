package com.jorfald.friender.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.jorfald.friender.FrienderApplication
import com.jorfald.friender.model.dataClasses.User
import com.jorfald.friender.viewModel.FriendsViewModel
import java.lang.IllegalArgumentException

class UserRepository {

    private lateinit var firebaseLogin: FirebaseAuth
    private val context = FrienderApplication.application.applicationContext
    private val friendsViewModel = FriendsViewModel()

    fun existingUser(email: String, password: String, callback: (Boolean, User) -> Unit) {
        firebaseLogin = FirebaseAuth.getInstance()
        try {
            firebaseLogin.signInWithEmailAndPassword(email, password).addOnCompleteListener { loginTask ->
                if (loginTask.isSuccessful) {
                    callback(true, User(email))
                }
            }.addOnFailureListener{ error ->
                error.localizedMessage?.let { friendsViewModel.toast(it)
                    callback(false, User(email))
                }
            }
        } catch (e: IllegalArgumentException) {
            Log.d("Login", "Bruker skrev ingenting.")


        }    }

    fun newUser(email: String, password: String, callback: (Boolean, User) -> Unit) {
        firebaseLogin = FirebaseAuth.getInstance()
        try {
            firebaseLogin.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { registerTask ->
                    if (registerTask.isSuccessful) {
                        callback(true, User(email))
                    }
                }.addOnFailureListener { error ->
                    error.localizedMessage?.let {
                        friendsViewModel.toast(it)
                        callback(false, User(email))
                    }
                }
        } catch (e: IllegalArgumentException) {
            Log.d("Login", "Bruker skrev ingenting.")

        }
    }
}