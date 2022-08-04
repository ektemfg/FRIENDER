package com.jorfald.friender

import android.content.Context
import androidx.room.Room
import com.jorfald.friender.Database.AppDatabase
import com.jorfald.friender.Database.DaoClass
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import androidx.test.core.app.ApplicationProvider
import com.jorfald.friender.model.dataClasses.Friend
import com.jorfald.friender.model.dataClasses.FriendEmployment
import com.jorfald.friender.model.dataClasses.FriendLocation
import java.io.IOException

@RunWith(JUnit4::class)
class DB_Test {
    var fakeFriend = Friend(1337,"x","x","x","x","x",
        FriendEmployment("x","x"), FriendLocation("x","x"),"x" )

    private lateinit var friendDB: AppDatabase
    private lateinit var friendDAO: DaoClass

    @Before
    fun createDB() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        friendDB = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        friendDAO = friendDB.getDao()
    }

    @After
    @Throws(IOException::class)
    fun kill() {
        friendDB.close()
    }

    @Test
    @Throws(Exception::class)
    fun friendDBInsert()  {
        friendDAO.insert(fakeFriend)
        val friends = friendDAO.getAll()
        val x: Boolean = friends.contains(fakeFriend)
        assertEquals(true, x)
    }

    @Test
    @Throws(Exception::class)
    fun friendDBRemove() {
        friendDAO.insert(fakeFriend)
        friendDAO.delete(fakeFriend.id)
        val friends = friendDAO.getAll()
        val result: Boolean = friends.contains(fakeFriend)
        assertEquals(false, result)
    }


    @Test
    @Throws(Exception::class)
    fun friendDBRemoveAll() {
        friendDAO.insert(fakeFriend)
        fakeFriend.id = 1338
        friendDAO.insert(fakeFriend)
        fakeFriend.id = 1339
        friendDAO.insert(fakeFriend)
        fakeFriend.id = 1340
        friendDAO.deleteAllFriends()
        val friends = friendDAO.getAll()
        val result: Boolean = friends.contains(fakeFriend)
        assertEquals(false, result)
    }

    @Test
    @Throws(Exception::class)
    fun dbGetByID() {
        friendDAO.insert(fakeFriend)
        val friend = friendDAO.getId(1337)
        val result: Boolean = friend.equals(fakeFriend)
        friendDAO.delete(fakeFriend.id)

        assertEquals(true, result)
    }
}