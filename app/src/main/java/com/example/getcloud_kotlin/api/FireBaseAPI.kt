package com.example.getcloud_kotlin.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class FireBaseAPI {

    private var _user = MutableLiveData<String>()
    val user: LiveData<String> get() = _user
    private var _checker = MutableLiveData<Boolean>()
    val checker : LiveData<Boolean> get()= _checker

    private val auth: FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
        _checker.value = false
    }

    fun signIN(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            Log.e("Tag", "Done")
            _user.value = auth.uid.toString()
            Log.e("Tag", _user.value.toString())

        }.addOnFailureListener {
            Log.e("Tag", it.toString())
        }
    }

    fun signUp(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            _user.value = auth.uid.toString()
            Log.v("Tag", _user.value.toString())
        }.addOnFailureListener { exception ->
            Log.v("Tag", exception.toString())
        }
    }

}
