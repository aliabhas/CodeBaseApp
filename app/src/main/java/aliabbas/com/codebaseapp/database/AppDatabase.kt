package aliabbas.com.codebaseapp.database

import aliabbas.com.codebaseapp.database.dao.CacheSearchedDataDao
import aliabbas.com.codebaseapp.database.dao.SearchedDetailsDao
import aliabbas.com.codebaseapp.database.entities.SearchedCache
import aliabbas.com.codebaseapp.models.pixabaymodels.Hits
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database configurations and instances were created here.
 */
@Database(
    entities = arrayOf(SearchedCache::class, Hits::class),
    version = 1
)
public abstract class AppDatabase : RoomDatabase() {


    abstract fun getCacheSearchedDataDao(): CacheSearchedDataDao
    abstract fun getSearchedDetailsDao(): SearchedDetailsDao

    /**
     * Function to get room database instance
     *
     * @param context [Context] instance
     * @return [AppDatabase.databaseInstance]
     */
    companion object {
        private val DB_NAME = "AppData.db"
        private var databaseInstance: AppDatabase? = null

        public fun getAppDatabase(context: Context): AppDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.inMemoryDatabaseBuilder(
                    context,
                    AppDatabase::class.java
                )
                    .build()
            }
            return databaseInstance!!
        }
    }
}