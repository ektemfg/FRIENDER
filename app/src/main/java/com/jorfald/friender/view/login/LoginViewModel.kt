package com.jorfald.friender.view.login

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorfald.friender.FrienderApplication
import com.jorfald.friender.model.dataClasses.User

import com.jorfald.friender.model.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class LoginViewModel: ViewModel() {

    var status: MutableLiveData<Boolean> = MutableLiveData()
    private val context = FrienderApplication.application.applicationContext
    private var repository: UserRepository = UserRepository()
    var loggedInUser: MutableLiveData<User> = MutableLiveData()


   fun login(email: String, password: String) {
       CoroutineScope(Dispatchers.IO).launch {
           repository.existingUser(email, password) {
               loginStatus, user ->
               loggedInUser.postValue(user)
               status.postValue(loginStatus)
           }
       }.start()
   }


    fun register(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.newUser(email, password) {
                    loginStatus, user ->
                loggedInUser.postValue(user)
                status.postValue(loginStatus)
            }
        }.start()
    }

    fun toast(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    }
