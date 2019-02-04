package room.sample.android.com.roomdatabase.model.databse.users

import android.annotation.SuppressLint
import android.app.Application
import android.arch.core.executor.ArchTaskExecutor
import android.arch.core.executor.ArchTaskExecutor.*
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import room.sample.android.com.roomdatabase.ViewModelRepository


@Database(entities = arrayOf(User::class),version = 1)
abstract class UserDatabase : RoomDatabase(){

    abstract fun getUserDao() : UserDao

    companion object {

        private var userDatabase: UserDatabase? =  null
        private const val DB_NAME = "user_database"

        @JvmStatic
        fun getInMemoryDataBase():UserDatabase{
            if( userDatabase == null){
                userDatabase = Room.inMemoryDatabaseBuilder(ViewModelRepository.getApplicationContext(),UserDatabase::class.java)
                        .allowMainThreadQueries()
                        .build()

            }
            return userDatabase as UserDatabase
        }

        fun getDataBase(appContext : Context):UserDatabase{
            Log.d("@manish UserDatabase", "we are UserDatabase getDataBase userdDatabase " + userDatabase)
            if( userDatabase == null){
                //file database
                Log.d("@manish UserDatabase", "creating DB")
                userDatabase = Room.databaseBuilder(appContext,UserDatabase::class.java,DB_NAME)
                        .build()
                        //.allowMainThreadQueries() // not advisable , this is to enforce the query to run on background tread
            }
            return userDatabase as UserDatabase
        }


        fun clearDataBase(){
            userDatabase = null
        }

    }


    /*
    If your app runs in a single "process", you should follow the singleton design pattern
    If your app runs in multiple processes, include enableMultiInstanceInvalidation() in your database builder invocation.
     */


}