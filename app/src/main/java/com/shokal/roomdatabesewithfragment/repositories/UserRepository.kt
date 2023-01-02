package com.shokal.roomdatabesewithfragment.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.LiveData
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.shokal.roomdatabesewithfragment.models.User

@Suppress("DEPRECATION")
class UserRepository(context: Context) {
    private val db: UserDao = UserDatabase.getDatabase(context).userDao()

    private val localContext = context    //Fetch All the Users
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


    suspend fun updateUser(user: User){
        db.update(user)
    }


//    suspend fun getBitmap(imageUrl: String): Bitmap {
//        val loading = ImageLoader(localContext)
//        val request = ImageRequest.Builder(localContext)
//            .data(imageUrl)
//            .build()
//
//        val result = (loading.execute(request) as SuccessResult).drawable
//        return (result as BitmapDrawable).bitmap
//    }
}