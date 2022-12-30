package com.shokal.roomdatabesewithfragment.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.shokal.roomdatabesewithfragment.models.User

@Suppress("DEPRECATION")
class UserRepository(context: Context) {
    private val db: UserDao = UserDatabase.getDatabase(context).userDao()

    //Fetch All the Users
    fun getAllUsers(): LiveData<List<User>> {
        return db.getAll()
    }

    // Insert new user
    suspend fun insertUser(user: User) {
        return db.insert(user)
    }

    // update user
    suspend fun deleteAllUser() {
        db.deleteAll()
    }

    // Delete user
    suspend fun deleteUser(users: User) {
        db.delete(users)
    }
}