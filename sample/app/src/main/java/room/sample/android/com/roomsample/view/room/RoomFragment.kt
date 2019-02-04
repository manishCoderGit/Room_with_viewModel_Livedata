package room.sample.android.com.roomsample.view.room

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import room.sample.android.com.roomdatabase.ViewModelRepositoryContract
import room.sample.android.com.roomdatabase.model.databse.users.User
import room.sample.android.com.roomdatabase.viewModel.androidViewModel.UserViewModelContract
import room.sample.android.com.roomsample.R
import room.sample.android.com.roomsample.SampleApplication

import kotlinx.android.synthetic.main.fragment_one.*


class RoomFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: UserListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager


    private lateinit var userViewModel: UserViewModelContract  // View model interface
    private val viewModelRepository: ViewModelRepositoryContract = SampleApplication.getSampleApplication().getViewModelRepository() // room module interface


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("@manish RoomFragment", "onCreate")
        super.onCreate(savedInstanceState)

        if (activity != null) {
            Log.d("@manish RoomFragment", "onCreate not null")
            userViewModel = viewModelRepository.registerWithViewAndGetUserViewModel(activity!!)
        }
    }

    private fun createObserverForUserList(): Unit {
        //OBSERVER
        val userObserver = Observer<MutableList<User>> {
            Log.d("@manish RoomFragment", "Observer onChanged() is called")
            if (null != it) {
                handleChangeInUserList(it)
            }
        }
        //we are pairing observer with LifeCycleOwner type
        userViewModel.getUserLiveData().observe(this, userObserver)

    }


    private fun handleChangeInUserList(usersList: MutableList<User>): Unit {
        Log.d("@manish RoomFragment", "handleChangeInUserList()")
        viewAdapter.updateData(usersList)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)

        viewAdapter = UserListAdapter(mutableListOf())

        recyclerView = view.findViewById(R.id.user_list_one)
        viewManager = LinearLayoutManager(activity)
        recyclerView.apply {
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        createObserverForUserList();

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_user.setOnClickListener(View.OnClickListener {
            userViewModel.addNewUser(userViewModel.createUser())
        })

        remove_user.setOnClickListener(View.OnClickListener {
            userViewModel.removeLastUser()
        })

        remove_all_user.setOnClickListener(View.OnClickListener {
            userViewModel.removeAllUser()
        })


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


}