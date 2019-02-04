package room.sample.android.com.roomdatabase


import android.app.Application
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import room.sample.android.com.roomdatabase.viewModel.androidViewModel.UserViewModel
import room.sample.android.com.roomdatabase.viewModel.androidViewModel.UserViewModelContract
import java.lang.Exception


class ViewModelRepository(public var app: Application) : ViewModelRepositoryContract {


    companion object {
        private lateinit var application: Application

        @JvmStatic
        fun getApplicationContext(): Context {
            Log.d("@manish VMRepository", "getApplicationContext()")
            if (application == null) {
                throw Exception("Application class is yet to be initalized")
            }
            return application.applicationContext
        }


        @JvmStatic
        fun getApplication(): Application {
            Log.d("@manish VMRepository", "getRoomApplication()")
            if (application == null) {
                throw Exception("Application class is yet to be initalized")
            }
            return application
        }

    }


    init {
        Log.d("@manish VMRepository", "init")
        application = app
    }


    override fun viewModelRepositoryInit() {
        //we can do some more init if required
    }

    @NonNull
    override fun registerWithViewAndGetUserViewModel(@NonNull activity: FragmentActivity): UserViewModelContract {
        return ViewModelProviders.of(activity).get(UserViewModel::class.java)
    }


    @NonNull
    override fun registerWithViewAndGetUserViewModel(fragment: Fragment): UserViewModelContract {
        return ViewModelProviders.of(fragment).get(UserViewModel::class.java)
    }
}