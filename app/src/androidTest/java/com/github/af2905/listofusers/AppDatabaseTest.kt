package com.github.af2905.listofusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.af2905.listofusers.repository.database.AppDatabase
import com.github.af2905.listofusers.repository.database.dao.UserDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    @Rule
    lateinit var instantTaskExecutorRule: InstantTaskExecutorRule
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun initRoom() {
        instantTaskExecutorRule = InstantTaskExecutorRule()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userDao = database.userDao()
    }

    @After
    fun afterTests() {
        database.close()
    }

    @Test
    fun checkLoadingAndReceivingData() {
        val users = AndroidTestHelper().users
        val onlyTwoUsers = listOf(users[0], users[1])
        userDao.insertList(onlyTwoUsers)
        val dbUsers = userDao.getAll()
        assertEquals(2, dbUsers.size)
    }

    @Test
    fun checkReceivingUserById() {
        val users = AndroidTestHelper().users
        userDao.insertList(users)
        val dbUsers = userDao.getAll()
        val firstUserId = dbUsers[0].id
        val user = userDao.getById(firstUserId)
        assertEquals(dbUsers[0], user)
    }
}