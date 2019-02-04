package room.sample.android.com.roomsample.view.room

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import room.sample.android.com.roomsample.R


class RoomActivity : AppCompatActivity(){



    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("@manish RoomActivity", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        if (null == savedInstanceState) {
            Log.d("@manish RoomActivity", "onCreate null")
            val fragmentTransaction = fragmentManager
                    .beginTransaction()
            val fragment_one = RoomFragment()

            fragmentTransaction.add(R.id.fragment1, fragment_one)
                    .commit();

        }
    }




    override fun onDestroy() {
        Log.d("@manish RoomActivity", "onDestroy")
        super.onDestroy()
    }
}