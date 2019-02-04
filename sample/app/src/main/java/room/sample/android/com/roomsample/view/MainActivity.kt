package room.sample.android.com.roomsample.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import room.sample.android.com.roomdatabase.ViewModelRepositoryContract
import room.sample.android.com.roomdatabase.model.databse.users.User
import room.sample.android.com.roomdatabase.viewModel.androidViewModel.UserViewModelContract
import room.sample.android.com.roomsample.R
import room.sample.android.com.roomsample.SampleApplication
import room.sample.android.com.roomsample.view.room.RoomActivity
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModelContract  // View model interface
    private val viewModelRepository: ViewModelRepositoryContract = SampleApplication.getSampleApplication().getViewModelRepository()

    companion object {
        private var id_Counter: Int = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("@manish MainActivity", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = viewModelRepository.registerWithViewAndGetUserViewModel(this)
        //create and link observer with Livedata
        createObserverForUserList();


        //add User
        addUser.setOnClickListener() {
            Log.d("@manish MainActivity", "add user")
            userViewModel.addNewUser(userViewModel.createUser())
        }


        deleteUser.setOnClickListener(){
            Log.d("@manish MainActivity", "delete user")
            userViewModel.removeLastUser()
        }

        removeAllUser.setOnClickListener(){
            Log.d("@manish MainActivity", "delete All user")
            userViewModel.removeAllUser()
        }


        //Next activity button
        clickToNextActivity.setOnClickListener() {
            val intent = Intent(this, RoomActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createObserverForUserList(): Unit {

        //OBSERVER
        val userObserver = Observer<MutableList<User>> { usersList: MutableList<User>? ->
            Log.d("@manish MainActivity", "Observer onChanged() is called")
            handleChangeInUserList(usersList)
        }

        //we are pairing observer with LifeCycleOwner type
        userViewModel.getUserLiveData().observe(this, userObserver)

    }


    private fun handleChangeInUserList(usersList: MutableList<User>?): Unit {
        var finalData: StringBuilder = StringBuilder()
        if (null != usersList) {
            for (user in usersList) {
                if (null != user) {
                    val (id, firstName, secondName, address) = user

                    finalData = finalData.append("\n" + "Name: $firstName $secondName  and Address: $address")
                }

            }
        }
        Log.d("@manish MainActivity", "finalData:  $finalData")
        userListText.setText(finalData)
    }

    override fun onResume() {
        super.onResume()
        if (userViewModel.getUserLiveData().hasActiveObservers()) {
            userViewModel.updateViewModel()
        }

    }


    override fun onDestroy() {
        Log.d("@manish MainActivity", "we are in onDestroy")
        super.onDestroy()
    }
}
