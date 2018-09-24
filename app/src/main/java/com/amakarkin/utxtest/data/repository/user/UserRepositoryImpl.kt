package com.amakarkin.utxtest.data.repository.user

import com.amakarkin.utxtest.data.models.User
import com.amakarkin.utxtest.data.models.UserDao
import com.amakarkin.utxtest.domain.user.UserRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override fun getUserData(login: String): Single<User> {
        return userDao.getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
    }

    override fun addUsers(users: List<User>): Completable {
        return Completable.fromCallable(UserAddCallable(users))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
    }

    override fun updateUser(user: User): Completable {
        return Completable.fromCallable(UserSetCallable(user))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
    }

    inner class UserAddCallable(private val users: List<User>) : Callable<List<User>> {
        override fun call(): List<User> {
            userDao.insertAll(users)
            return users
        }
    }

    inner class UserSetCallable(private val user: User) : Callable<User> {
        override fun call(): User {
            userDao.insertAll(listOf(user))
            return user
        }
    }
}