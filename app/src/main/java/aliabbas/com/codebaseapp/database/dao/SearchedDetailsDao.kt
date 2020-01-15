package aliabbas.com.codebaseapp.database.dao

import aliabbas.com.codebaseapp.database.entities.SearchedCache
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

/**
 * Created By Ali Abbas on on 12,January,2020
 * This Class is used for
 *
 */
@Dao
public abstract class SearchedDetailsDao {

    @Query("Select searchedString from SearchedCache")
    abstract fun getCacheData(): LiveData<List<String>>

}