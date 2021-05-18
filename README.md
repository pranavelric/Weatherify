# Weatherify

Weatherify fetches data from the OpenWeatherMap API to provide real time weather information. Weatherfiy is implemented using the MVVM single activity architecture, Retrofit2, Dagger-Hilt, LiveData, Coroutines, Room, Navigation Components, View  Binding and some other libraries from the Android Jetpack. 

# Setup API Key
- Visit openweathermap.org to get your API Key
- Open app/src/main/java/com/weather/weatherify/utils/Constants.kt 
- And add following line:

    const val API_KEY_VALUE = "your api key here"
  

# Technologies used

* [openweather api](https://openweathermap.org/api) provides weather data for any location.
* [MVVM](https://developer.android.com/jetpack/guide?gclid=CjwKCAjwqIiFBhAHEiwANg9szhEQU8xNXU6Qc9Mk1igWc5Q4iKST31MFsBTihfn7Zo4g6HU0CAthFBoCpoIQAvD_BwE&gclsrc=aw.ds) Model-View-ViewModel (MVVM) is a structural design pattern that separates objects into model, view and view model.
* [Dagger-hilt](https://developer.android.com/training/dependency-injection/hilt-android) a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
* [Retrofit](https://square.github.io/retrofit/) a REST Client for Android which makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice.
* [Glide](https://github.com/bumptech/glide) a fast and efficient open source media management and image loading framework for Android.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) to handle data in a lifecycle-aware fashion.
* [Navigation Component](https://developer.android.com/guide/navigation) to handle all navigations and also passing of data between destinations.
* [Material Design](https://material.io/develop/android/docs/getting-started/) an adaptable system of guidelines, components, and tools that support the best practices of user interface design.
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) used to manage the local storage i.e. `writing to and reading from the database`. Coroutines help in managing background threads and reduces the need for callbacks.
* [View Binding](https://developer.android.com/topic/libraries/view-binding) is feature that allows you to more easily write code that interacts with views.
* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library which provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.




# Features
- Detailed Weather Info By Location
- Detailed weather Forecast
- Search weather By City
- Dark Theme available

# Screenshots

<img src="screenshots/1weather.png" >
<img src="screenshots/2weather.png" >
<img src="screenshots/3weather.png" >
<img src="screenshots/4weather.png" >
<img src="screenshots/5weather.png" >
<img src="screenshots/6weather.png" >
<img src="screenshots/7weather.png" >


# Contribution
All contributions are welcome. If you are interested in seeing a particular feature implemented in this app, please open a new issue so after which you can make a PR!
