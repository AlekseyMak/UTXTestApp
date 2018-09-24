package com.amakarkin.utxtest.domain.user

import com.amakarkin.utxtest.data.models.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun getUserData(login: String): Single<User>

    fun addUsers(users: List<User>): Completable

    fun updateUser(user: User): Completable
}