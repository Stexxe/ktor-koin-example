package koinexample.repositories

import koinexample.models.User

class InMemoryUserRepository: UserRepository {

    private var users = mutableListOf<User>()

    override fun getAll(): List<User> {
        return users
    }

    override fun insert(user: User): User? {
        users.add(user)
        return user
    }

}