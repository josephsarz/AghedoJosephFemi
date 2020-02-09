package codegene.femicodes.aghedojosephfemi.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import codegene.femicodes.aghedojosephfemi.local.db.AppDatabase
import codegene.femicodes.aghedojosephfemi.local.entity.CarOwnerEntity
import codegene.femicodes.aghedojosephfemi.ui.utils.CSV_FILENAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.*

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO) {

            try {
                applicationContext.assets.open(CSV_FILENAME).use { inputStream ->
                    BufferedReader(
                        InputStreamReader(
                            inputStream,
                            Charset.forName("UTF-8")
                        )
                    ).use { reader ->
                        var line = ""
                        val carOwnerList: MutableList<CarOwnerEntity> = ArrayList()
                        while (reader.readLine() != null) {
                            reader.readLine().also { line = it }
                            val ownerCsv: List<String> = line.split(",")
                            val ownerObj = CarOwnerEntity(
                                ownerCsv[0],
                                ownerCsv[1],
                                ownerCsv[2],
                                ownerCsv[3],
                                ownerCsv[4],
                                ownerCsv[5],
                                ownerCsv[6],
                                ownerCsv[7],
                                ownerCsv[8],
                                ownerCsv[9],
                                ownerCsv[10]
                            )
                            carOwnerList.add(ownerObj)
                        }
                        AppDatabase.getInstance(applicationContext).carOwnerDao()
                            .insertAll(carOwnerList)
                        Result.success()
                    }
                }

            } catch (e: Exception) {
                Timber.e(e, "Error seeding database")
                Result.failure()
            }


        }
    }
}