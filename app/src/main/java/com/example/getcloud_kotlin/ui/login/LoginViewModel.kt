package com.example.getcloud_kotlin.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loading = MutableLiveData(false)
    private val _user = MutableLiveData<String>()

    val loading: LiveData<Boolean> get() = _loading
    val user: LiveData<String> get() = _user

    fun signIN(email: String, password: String): String {
        _loading.value = true
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            if (it != null) {
                _loading.value = false
                _user.value = auth.uid.toString()
                Log.e("Tag", "Done")
                Log.e("Tag", _user.value.toString())
            }
        }.addOnFailureListener {
            _loading.value = false
            Log.e("Tag", it.toString())
        }
        return _user.value.toString()
    }
}