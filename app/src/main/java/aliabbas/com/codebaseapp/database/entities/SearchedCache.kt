package aliabbas.com.codebaseapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created By Ali Abbas on on 12,January,2020
 * This Class is used for
 *
 */
@Entity
class SearchedCache {
    @PrimaryKey(autoGenerate = true)
    var searchedCacheId: Int? = null
    var searchedString: String? = null
}