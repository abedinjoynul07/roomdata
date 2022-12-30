package com.shokal.roomdatabesewithfragment.repositories

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shokal.roomdatabesewithfragment.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<User>>

    /* @Query("SELECT * FROM student_table WHERE uid IN (:userIds)")
     fun loadAllByIds(userIds: IntArray): List<Student>*/

    @Query("SELECT * FROM users WHERE id LIKE :roll LIMIT 1")
    suspend fun findByRoll(roll: Int): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}