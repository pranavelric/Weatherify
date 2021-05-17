package com.weather.weatherify.data.remote

import com.movies.animefied.utils.ResponseState
import com.weather.weatherify.data.model.*
import com.weather.weatherify.data.remote.api.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(private val weatherApi: WeatherApi) :
    WeatherRemoteDataSource {

    //weather
    override suspend fun getWeatherByLocation(coord: Coord): ResponseState<ResponseWeather?> =
        withContext(Dispatchers.IO) {


            return@withContext try {
                val result =
                    weatherApi.getWeatherByLocation(coord.lat.toString(), coord.lon.toString())
                if (result.isSuccessful) {
                    val weather = result.body()
                    ResponseState.Success(weather)
                } else {
                    ResponseState.Success(null)
                }

            } catch (e: Exception) {
                if (e.message != null)
                    ResponseState.Error(e.message!!)
                else {
                    ResponseState.Error("Some error occured!")
                }
            }
        }

    override suspend fun getWeatherByZipAndCountry(zipAndCountry: ZipAndCountryCode): ResponseState<ResponseWeather?> =
        withContext(Dispatchers.IO) {


            return@withContext try {
                val result =
                    weatherApi.getWeatherByZipAndContryCode(zipAndCountry.zipCode + "," + zipAndCountry.countryCode)
                if (result.isSuccessful) {
                    val weather = result.body()
                    ResponseState.Success(weather)
                } else {
                    ResponseState.Success(null)
                }

            } catch (e: Exception) {
                if (e.message != null)
                    ResponseState.Error(e.message!!)
                else {
                    ResponseState.Error("Some error occured!")
                }
            }
        }

    override suspend fun getWeatherByCityName(name: String): ResponseState<ResponseWeather?> =
        withContext(Dispatchers.IO) {


            return@withContext try {
                val result =
                    weatherApi.getWeatherByCityName(name)
                if (result.isSuccessful) {
                    val weather = result.body()
                    ResponseState.Success(weather)
                } else {
                    ResponseState.Success(null)
                }

            } catch (e: Exception) {
                if (e.message != null)
                    ResponseState.Error(e.message!!)
                else {
                    ResponseState.Error("Some error occured!")
                }
            }
        }

    override suspend fun getWeatherByCityId(id: Int): ResponseState<ResponseWeather?> =
        withContext(Dispatchers.IO) {


            return@withContext try {
                val result =
                    weatherApi.getWeatherByCityID(id.toString())
                if (result.isSuccessful) {
                    val weather = result.body()
                    ResponseState.Success(weather)
                } else {
                    ResponseState.Success(null)
                }

            } catch (e: Exception) {
                if (e.message != null)
                    ResponseState.Error(e.message!!)
                else {
                    ResponseState.Error("Some error occured!")
                }
            }
        }


    //oneCall
    override suspend fun getOneCallWeatherForecast(coord: Coord, units: String): ResponseState<ResponseOneCallWeatherForecast?> = withContext(Dispatchers.IO) {


        return@withContext try {
            val result =
                weatherApi.getWeatherForecast(coord.lat.toString(), coord.lon.toString(), units)
            if (result.isSuccessful) {
                val weather = result.body()
                ResponseState.Success(weather)
            } else {
                ResponseState.Success(null)
            }

        } catch (e: Exception) {
            if (e.message != null)
                ResponseState.Error(e.message!!)
            else {
                ResponseState.Error("Some error occured!")
            }
        }
    }

    // forecast
    override suspend fun getWeatherForecastByCityName(name: String): ResponseState<List<NetworkWeatherForecast>?> = withContext(Dispatchers.IO) {


            return@withContext try {
                val result =
                    weatherApi.getWeatherForecastByCityName(name)
                if (result.isSuccessful) {
                    val weather = result.body()?.list
                    ResponseState.Success(weather)
                } else {
                    ResponseState.Success(null)
                }

            } catch (e: Exception) {
                if (e.message != null)
                    ResponseState.Error(e.message!!)
                else {
                    ResponseState.Error("Some error occured!")
                }
            }
        }
    override suspend fun getWeatherForecastByLocation(coord: Coord): ResponseState<List<NetworkWeatherForecast>?> = withContext(Dispatchers.IO) {


            return@withContext try {
                val result =
                    weatherApi.getWeatherForecastByLocation(
                        coord.lat.toString(),
                        coord.lon.toString()
                    )
                if (result.isSuccessful) {
                    val weather = result.body()?.list
                    ResponseState.Success(weather)
                } else {
                    ResponseState.Success(null)
                }

            } catch (e: Exception) {
                if (e.message != null)
                    ResponseState.Error(e.message!!)
                else {
                    ResponseState.Error("Some error occured!")
                }
            }
        }
}