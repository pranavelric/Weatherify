package com.weather.weatherify.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.weather.weatherify.data.database.entities.DBWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [DBWeather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun dao(): WeatherDao

    companion object {
        val DATABASE_NAME = "WEAHTER_DATABASE"
    }

    class Callback @Inject constructor(
        private val database: Provider<WeatherDatabase>,
        private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = database.get().dao()
            applicationScope.launch {
                // do pre-populating here
            }
        }

    }

}