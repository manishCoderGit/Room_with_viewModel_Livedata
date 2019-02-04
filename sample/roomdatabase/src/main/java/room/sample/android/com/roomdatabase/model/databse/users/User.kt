package room.sample.android.com.roomdatabase.model.databse.users

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "Users")
data class User(
        @PrimaryKey @ColumnInfo(name = "user_id") var id: Int,
        var firstName: String,
        var lastName: String,
        var address: String?
)