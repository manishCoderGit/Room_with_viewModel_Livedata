package room.sample.android.com.roomdatabase.model.databse.users

import android.arch.persistence.room.*
import android.support.annotation.NonNull


@Dao
public interface UserDao {

    @Query("SELECT * FROM Users")
    fun getAllUser() : MutableList<User>


    @Query("SELECT * FROM Users WHERE user_id = :id ")
    fun getUserWithId(id:Int) : User?


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(@NonNull user : User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(@NonNull user : List<User>)



    @Delete
    fun deleteUser(@NonNull user: User)

    @Query("DELETE FROM Users WHERE firstName=:firstName")
    fun deleteUserByFirstName(@NonNull firstName : String)

    @Query("DELETE from Users")
    fun deleteAll()


}