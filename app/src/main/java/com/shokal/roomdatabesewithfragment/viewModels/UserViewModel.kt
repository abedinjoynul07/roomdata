package com.shokal.roomdatabesewithfragment.viewModels

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shokal.roomdatabesewithfragment.models.User
import com.shokal.roomdatabesewithfragment.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    var userList: LiveData<List<User>>
    var userRepo: UserRepository

    init {
        userRepo = UserRepository(application)
        userList = userRepo.getAllUsers()
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.insertUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.deleteUser(user)
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.deleteAllUser()
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.updateUser(user)
        }
    }
}