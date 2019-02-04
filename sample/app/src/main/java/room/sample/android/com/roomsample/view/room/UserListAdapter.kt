package room.sample.android.com.roomsample.view.room

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import room.sample.android.com.roomsample.R
import room.sample.android.com.roomdatabase.model.databse.users.User


class UserListAdapter(private var userList: MutableList<User>) : RecyclerView.Adapter<UserListAdapter.UserViewModel>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewModel {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.user_list_item, viewGroup, false)
        return UserViewModel(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateData(list: MutableList<User>) {
        Log.d("@manish Adapter", "updateData userList :" + list.size)
        this.userList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(userViewModel: UserViewModel, position: Int) {
        val user: User = userList.get(position)
        userViewModel.firstNameTextView.text = user.firstName
        userViewModel.lastNameTextView.text = user.lastName
        userViewModel.address.text = user.address
        userViewModel.id.text = "" + (user).id
    }




    //this is automatically static nested class in kotlin
    class UserViewModel(private val view: View) : RecyclerView.ViewHolder(view) {

        var firstNameTextView: TextView
        var lastNameTextView: TextView
        var address: TextView
        var id: TextView

        init {
            firstNameTextView = view.findViewById(R.id.first_text)
            lastNameTextView = view.findViewById(R.id.last_text)
            address = view.findViewById(R.id.address_text)
            id = view.findViewById(R.id.id_text)
        }


    }

}