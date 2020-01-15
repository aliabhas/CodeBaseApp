package aliabbas.com.codebaseapp.models.pixabaymodels

import aliabbas.com.codebaseapp.database.entities.SearchedCache
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created By Ali Abbas on on 12,January,2020
 * This Class is used for
 *
 */
@Parcelize
@Entity
class Hits : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var hintsId: Int? = null
    var searchedQuery: String? = null
    var largeImageURL: String? = null
    var webformatHeight: String? = null
    var webformatWidth: String? = null
    var likes: String? = null
    var imageWidth: String? = null
    var id: String? = null
    var user_id: String? = null
    var views: String? = null
    var comments: String? = null
    var pageURL: String? = null
    var imageHeight: String? = null
    var webformatURL: String? = null
    var type: String? = null
    var previewHeight: String? = null
    var tags: String? = null
    var downloads: String? = null
    var user: String? = null
    var favorites: String? = null
    var imageSize: String? = null
    var previewWidth: String? = null
    var userImageURL: String? = null
    var previewURL: String? = null
}