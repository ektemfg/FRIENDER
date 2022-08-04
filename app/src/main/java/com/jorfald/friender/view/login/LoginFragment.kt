package com.jorfald.friender.view.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jorfald.friender.R

class LoginFragment : Fragment() {
    private val loginViewModel = LoginViewModel()
    lateinit var userField: TextView
    lateinit var passwordField: TextView
    lateinit var login: Button
    lateinit var register: TextView
    lateinit var emailTextField: TextView
    lateinit var passwordTextField: TextView
    lateinit var title: TextView

    private lateinit var firebaseLogin: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        title = view.findViewById(R.id.welcome)
        userField = view.findViewById(R.id.login_email)
        passwordField = view.findViewById(R.id.login_password)
        login = view.findViewById(R.id.login)
        register = view.findViewById(R.id.register_text)
        emailTextField = view.findViewById(R.id.email)
        passwordTextField = view.findViewById(R.id.password)
        bindButtons(view)
        loginViewModel.status.observe(viewLifecycleOwner) { status ->
            if (status) {
                try {
                    var email = userField.text.toString()
                    loginViewModel.toast("Welcome back, $email")
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToFriendsFragment())
                } catch (e: IllegalArgumentException) {
                    Log.d("Navigation", "Visste dette var en dårlig ide")
                }
            } else {
                Log.d("LoginStatus", "login failed.")
            }
        }


    }


    private fun bindButtons(view: View) {
       existingUserView()
    }

    private fun existingUserView() {
        title.text = "Welcome back!"
        login.text = "Login"
        register.visibility = View.VISIBLE
        register.text = "Register"
        emailTextField.text = "Email:"
        passwordTextField.text = "Password:"
        login.setOnClickListener {
            val email = userField.text.toString()
            val password = passwordField.text.toString()
            loginViewModel.login(email, password)
        }
        register.setOnClickListener{
            newUserView()
        }
    }

    private fun newUserView() {
        title.text = "Create new account!"
        login.text = "Register"
        register.text = "Login"
        emailTextField.text = "Which email you want to use?"
        passwordTextField.text = "And your super secret password is?"
        login.setOnClickListener {
            val email = userField.text.toString()
            val password = passwordField.text.toString()
            loginViewModel.register(email, password)
        }
        register.setOnClickListener{
            existingUserView()
        }
    }
}
