package room.sample.android.com.roomdatabase.repository.database

import android.content.Context
import android.util.Log
import room.sample.android.com.roomdatabase.model.databse.users.User
import room.sample.android.com.roomdatabase.model.databse.users.UserDao
import room.sample.android.com.roomdatabase.model.databse.users.UserDatabase
import room.sample.android.com.roomdatabase.repository.UserRepositoryContract


class UserFromDataBase constructor(val appContext : Context): UserRepositoryContract {

    lateinit var userDatabase: UserDatabase
    lateinit var userDao: UserDao

    init {
        Log.d("@manish", "we are UserViewModel init")
        userDatabase = UserDatabase.getDataBase(appContext)
        userDao = userDatabase.getUserDao()
    }

    override fun getListOfAllUser(): MutableList<User> {
        return userDao.getAllUser()
    }

    override fun getUserWithID(id: Int): User? {
        return userDao.getUserWithId(id)
    }


    override fun addUsers(userList: List<User>) {
        Log.d("@manish UserFromDB", "addNewUser" + userList.get(0).toString())
        userDao.insertUsers(userList)
    }

    override fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun deleteAllUsers() {
        userDao.deleteAll()
    }

}