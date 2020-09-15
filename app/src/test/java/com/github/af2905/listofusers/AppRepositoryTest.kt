package com.github.af2905.listofusers

import com.github.af2905.listofusers.repository.AppRepository
import com.github.af2905.listofusers.repository.database.AppDatabase
import com.github.af2905.listofusers.repository.server.ServerCommunicator
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test

class AppRepositoryTest {
    @MockK
    private lateinit var serverCommunicator: ServerCommunicator

    @MockK
    private lateinit var appDatabase: AppDatabase

    @InjectMockKs
    private lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun checkDataFlowFromServerCommunicator() {
        with(TestHelper()) {
            val users = createListOfUsers()
            every { appDatabase.userDao().getAll() } returns users
            every { serverCommunicator.getUsers() } returns Single.just(
                appDatabase.userDao().getAll()
            )
            checkDataFlow(appRepository.getAllUsersFromNetwork(), users, TestObserver())
        }
    }

    @Test
    fun checkDataFlowFromDatabase() {
        with(TestHelper()) {
            val users = createListOfUsers()
            every { appDatabase.userDao().getAll() } returns users
            checkDataFlow(appRepository.getAllUsersFromDatabase(), users, TestObserver())
        }
    }
}