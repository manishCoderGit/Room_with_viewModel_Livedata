package room.sample.android.com.roomdatabase


import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import room.sample.android.com.roomdatabase.viewModel.androidViewModel.UserViewModelContract


interface ViewModelRepositoryContract {

    fun viewModelRepositoryInit()

    @NonNull
    fun registerWithViewAndGetUserViewModel(@NonNull activity: FragmentActivity): UserViewModelContract

    @NonNull
    fun registerWithViewAndGetUserViewModel(@NonNull fragment: Fragment): UserViewModelContract

}