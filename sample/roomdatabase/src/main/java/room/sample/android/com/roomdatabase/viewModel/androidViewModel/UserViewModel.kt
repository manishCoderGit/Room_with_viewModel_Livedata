package room.sample.android.com.roomdatabase.viewModel.androidViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.NonNull
import android.util.Log
import room.sample.android.com.roomdatabase.repository.UserRepositoryContract
import room.sample.android.com.roomdatabase.model.databse.users.User
import android.os.AsyncTask
import room.sample.android.com.roomdatabase.ViewModelRepository
import room.sample.android.com.roomdatabase.repository.UserRepository


class UserViewModel() : ViewModel(), UserViewModelContract {


    private lateinit var userRepository: UserRepositoryContract


    companion object {
        private var ID_COUNTER = 0
    }


    init {
        Log.d("@manish UserVM", "we are in UserViewModel init")
        userRepository = UserRepository(ViewModelRepository.getApplicationContext())
    }


    //Live data , getUsersList() will be called only once , when wew will use userList wil called/used for the first time
    private val usersList: MutableLiveData<MutableList<User>> by lazy { getUsersListOnCall() }

    private fun getUsersListOnCall(): MutableLiveData<MutableList<User>> {
        Log.d("@manish UserVM", "we are getUsersList LAZY")
        insertSomeInitialDataInDB()
        val temp = MutableLiveData<MutableList<User>>()
        updateDataInLiveData() //this will update usersList with some more data
        return temp
    }

    override fun getUserLiveData(): LiveData<MutableList<User>> {
        Log.d("@manish UserVM", "we are getUserLiveData")
        return usersList
    }


    private fun insertSomeInitialDataInDB(): Unit {
        //adding 2 users
        val list: List<User> = listOf(createUser())
        addNewUsers(list)
    }


    private fun updateDataInLiveData() {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                usersList.postValue(userRepository.getListOfAllUser())
                return null
            }
        }.execute()
    }


/*
* Below are method used via ViewModel in any Activity/Fragment/LifeCycle
*
*/


    override fun addNewUser(user: User) {
        Log.d("@manish UserVM", "addNewUser")

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                userRepository.addUsers(listOf(user))
                return null
            }

            override fun onPostExecute(result: Void?) {
                updateDataInLiveData()
            }
        }.execute()

    }

    override fun removeLastUser() {
        Log.d("@manish UserVM", "removeUser")
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val list: MutableList<User> = userRepository.getListOfAllUser()
                if (list.size > 0) {
                    userRepository.deleteUser(list.get(list.size - 1))
                }
                return null
            }

            override fun onPostExecute(result: Void?) {
                updateDataInLiveData()
            }

        }.execute()

    }

    override fun removeAllUser() {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                userRepository.deleteAllUsers()
                return null
            }

            override fun onPostExecute(result: Void?) {
                updateDataInLiveData()
            }
        }.execute()

    }

    override fun addNewUsers(userList: List<User>) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                userRepository.addUsers(userList)
                //after this additon , we will update the latest data as well
                usersList.postValue(userRepository.getListOfAllUser())
                return null
            }

            override fun onPostExecute(result: Void?) {
                updateDataInLiveData()
            }
        }.execute()


    }

    override fun getListOFUser(): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun updateViewModel() {
        updateDataInLiveData()
    }


    override fun createUser(): User {
        return User(ID_COUNTER++, "Manish $ID_COUNTER", "Sahu $ID_COUNTER", "address of Manish $ID_COUNTER")
    }


    //The only method of ViewModel
    override fun onCleared() {
        Log.d("@manish UserVM", "we are onCleared")
        cleanUp();
        super.onCleared()
    }


    private fun cleanUp() {
        //we can perform cleaning of resources at this place
    }

}