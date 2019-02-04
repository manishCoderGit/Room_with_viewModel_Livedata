package room.sample.android.com.roomdatabase.repository

import android.support.annotation.NonNull
import room.sample.android.com.roomdatabase.model.databse.users.User


interface UserRepositoryContract {


    fun getListOfAllUser(): MutableList<User>

    fun getUserWithID(id: Int): User?

    fun addUsers(@NonNull userList: List<User>)

    fun deleteUser(@NonNull user: User)

    fun deleteAllUsers()

}