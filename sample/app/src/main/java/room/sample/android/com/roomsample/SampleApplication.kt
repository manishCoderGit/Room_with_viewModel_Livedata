package room.sample.android.com.roomsample

import android.app.Application
import room.sample.android.com.roomdatabase.ViewModelRepository
import room.sample.android.com.roomdatabase.ViewModelRepositoryContract
import java.lang.Exception


class SampleApplication : Application() {

    private lateinit var viewModelRepository: ViewModelRepositoryContract


    companion object {

        private lateinit var sampleApplication: SampleApplication

        @JvmStatic
        fun getApplication(): Application {
            if (sampleApplication == null) {
                throw Exception("Application class is yet to be initalized")
            }
            return sampleApplication as Application
        }

        @JvmStatic
        fun getSampleApplication(): SampleApplication {
            if (sampleApplication == null) {
                throw Exception("Application class is yet to be initalized")
            }
            return sampleApplication
        }

    }


    override fun onCreate() {
        super.onCreate()
        sampleApplication = this
        initRoomDataBase();
    }

    private fun initRoomDataBase() {
        viewModelRepository = ViewModelRepository(sampleApplication);
    }


    fun getViewModelRepository(): ViewModelRepositoryContract {
        return viewModelRepository;
    }
}