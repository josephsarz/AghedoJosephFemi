package codegene.femicodes.aghedojosephfemi.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import codegene.femicodes.aghedojosephfemi.domain.Converters
import codegene.femicodes.aghedojosephfemi.local.dao.CarOwnerDao
import codegene.femicodes.aghedojosephfemi.local.dao.FilterDao
import codegene.femicodes.aghedojosephfemi.local.entity.CarOwnerEntity
import codegene.femicodes.aghedojosephfemi.local.entity.FilterEntity
import codegene.femicodes.aghedojosephfemi.worker.SeedDatabaseWorker


@Database(
    entities = [CarOwnerEntity::class, FilterEntity::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carOwnerDao(): CarOwnerDao

    abstract fun filterDao(): FilterDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance
                ?: synchronized(this) {
                    instance
                        ?: buildDatabase(
                            context
                        ).also { instance = it }
                }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "cars-db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }
}
