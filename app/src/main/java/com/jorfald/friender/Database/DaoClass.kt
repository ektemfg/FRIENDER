package com.jorfald.friender.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jorfald.friender.model.dataClasses.Friend

@Dao
interface DaoClass {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Friend)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFriend(friend: Friend)

    @Query("SELECT * FROM friends")
    fun getAll(): List<Friend>

    @Query("SELECT * FROM friends WHERE id = :friendID")
    fun getId(friendID: Long): Friend

    @Query("DELETE FROM friends WHERE id = :id")
    fun delete(id: Long)


    @Query("DELETE FROM friends")
    fun deleteAllFriends()
}