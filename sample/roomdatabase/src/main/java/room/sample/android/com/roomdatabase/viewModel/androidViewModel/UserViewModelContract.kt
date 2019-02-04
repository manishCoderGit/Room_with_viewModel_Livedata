package room.sample.android.com.roomdatabase.viewModel.androidViewModel

import android.arch.lifecycle.LiveData
import android.support.annotation.NonNull
import room.sample.android.com.roomdatabase.model.databse.users.User

interface UserViewModelContract {


    fun getUserLiveData(): LiveData<MutableList<User>>

    fun addNewUser(@NonNull user: User)

    fun removeLastUser()

    fun removeAllUser()

    fun addNewUsers(@NonNull userList: List<User>)

    fun getListOFUser(): List<User>  // in case of view model design , we need not need to worry about this

    fun updateViewModel()  // in case of view model design , we need not need to worry about this

    //just to create user , in real example this will not be of any use
    fun createUser(): User

}