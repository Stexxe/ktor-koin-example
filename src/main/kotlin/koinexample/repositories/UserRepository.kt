package koinexample.repositories

import koinexample.models.User

interface UserRepository {

    fun getAll(): List<User>

    fun insert(user: User): User?

}