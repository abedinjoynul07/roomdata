package com.shokal.roomdatabesewithfragment.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shokal.roomdatabesewithfragment.models.User
import com.shokal.roomdatabesewithfragment.repositories.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application){
    var userList: LiveData<List<User>>
    var userRepo: UserRepository

    init {
        userRepo = UserRepository(application)
        userList = userRepo.getAllUsers()
    }
    fun addUser(user: User){
        GlobalScope.launch {
            userRepo.insertUser(user)
        }
    }

    fun deleteUser(user: User){
        GlobalScope.launch {
            userRepo.deleteUser(user)
        }
    }

    fun deleteAllUser(){
        GlobalScope.launch {
            userRepo.deleteAllUser()
        }
    }
}