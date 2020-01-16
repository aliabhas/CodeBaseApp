package aliabbas.com.codebaseapp.database.dao

import aliabbas.com.codebaseapp.database.entities.SearchedCache
import aliabbas.com.codebaseapp.models.pixabaymodels.Hits
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import io.reactivex.SingleObserver

/**
 * Created By Ali Abbas on on 12,January,2020
 * This Class is used for
 *
 */
@Dao
public abstract class CacheSearchedDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertCacheData(searchedCache: SearchedCache)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertCacheResult(lstHints: List<Hits>)

    @Query("Select * from Hits Where tags LIKE :searchedQuery")
    abstract fun getCacheData(searchedQuery: String): Single<List<Hits>>


}