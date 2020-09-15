package com.github.af2905.listofusers

import com.github.af2905.listofusers.repository.database.pojo.UserResponse
import com.github.af2905.listofusers.repository.server.ApiService
import com.github.af2905.listofusers.repository.server.ServerCommunicator
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test

class ServerCommunicatorTest {
    @MockK
    private lateinit var apiService: ApiService

    @InjectMockKs
    private lateinit var serverCommunicator: ServerCommunicator

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun checkDataFlowFromApiService() {
        with(TestHelper()) {
            val users = createListOfUsers()
            val userResponse = mockk<UserResponse>()
            every { userResponse.data } returns users
            every { apiService.getUsers() } returns Single.fromObservable(
                Observable.fromArray(userResponse)
            )
            checkDataFlow(serverCommunicator.getUsers(), users, TestObserver())
        }
    }
}