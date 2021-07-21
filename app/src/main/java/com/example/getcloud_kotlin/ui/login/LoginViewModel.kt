package com.example.getcloud_kotlin.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.getcloud_kotlin.api.FireBaseAPI
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class LoginViewModel() : ViewModel() {
//    private val api: FireBaseAPI
    private val auth: FirebaseAuth
    private val _checker = MutableLiveData<Boolean>()
    val checker: LiveData<Boolean> get() = _checker
    private val _user = MutableLiveData<String>()
    val user: LiveData<String> get() = _user

    init {
        auth = FirebaseAuth.getInstance()
//        api = FireBaseAPI()
        _checker.value = false
    }

    fun signN(email: String, password: String) {
//        if (email.isEmpty() && password.isEmpty()) {
//            Log.v("Tag", "Blank labels")
//        } else {
//            _user.value = api.user.value
//        }
    }

    fun signIN(email: String, password: String) : String {

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            if (it != null)
            {
                _checker.value = true
                _user.value = auth.uid.toString()
                Log.e("Tag", "Done")
                Log.e("Tag", _user.value.toString())
            }


        }.addOnFailureListener {
            Log.e("Tag", it.toString())
            _checker.value = false
        }
        return _user.value.toString()
    }

    fun onSignInCompelete() {
        _checker.value = false
    }

}