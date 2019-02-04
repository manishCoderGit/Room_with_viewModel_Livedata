package room.sample.android.com.roomdatabase.repository

import android.content.Context
import room.sample.android.com.roomdatabase.model.databse.users.User
import room.sample.android.com.roomdatabase.repository.database.UserFromDataBase


class UserRepository constructor(val appContext : Context): UserRepositoryContract {

    //The business logic of fetching data either form server or from database will be at this place
    // we can use factory pattern OR other business logic to select either one or as many as data source Currently we are only using DB
    var dataSource : UserRepositoryContract = UserFromDataBase(appContext)

    override fun getListOfAllUser(): MutableList<User> {
        return dataSource.getListOfAllUser()
    }

    override fun getUserWithID(id: Int): User? {
        return dataSource.getUserWithID(id)
    }


    override fun addUsers(userList: List<User>) {
        dataSource.addUsers(userList)
    }

    override fun deleteUser(user: User) {
        dataSource.deleteUser(user)
    }

    override fun deleteAllUsers() {
        dataSource.deleteAllUsers()
    }



}