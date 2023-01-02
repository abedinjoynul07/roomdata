package com.shokal.roomdatabesewithfragment.models

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?,
    val email: String?,
    val age: Int?,
    val profilePhoto: String
):Parcelable
