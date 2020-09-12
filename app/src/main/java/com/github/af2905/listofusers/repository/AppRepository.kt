package com.github.af2905.listofusers.repository

import com.github.af2905.listofusers.repository.database.AppDatabase
import com.github.af2905.listofusers.repository.server.ServerCommunicator

class AppRepository(private val communicator: ServerCommunicator, private val db: AppDatabase) {

}