# Android-MVVM-Weather-In-Tamriel-App

Table of contents
=================

<!--ts-->
   * [Introduction](#weather-in-tamriel)
   * [Final Product](#final-product)
   * [How To Run The App](#how-to-run-the-app)
   * [Development](#an-agile-approach)
      * [An Agile Approach](#an-agile-approach)
      * [Why MVVM/Architecture Components?](#why-mvvm-and-architecture-components)
      * [Why Dagger?](#why-dagger)
      * [Why RxJava?](#why-rxjava)
      * [Why Epoxy?](#why-epoxy)
	  * [Why Docker](#why-docker)
   * [Aditional reading on Android MVVM](#aditional-reading-on-android-mvvm)
   * [Questions?](#questions)
   * [Resources That Made This Project Possible](#resources-that-made-this-project-possible)
<!--te-->


Weather in Tamriel
=================
Weather in Tamriel is a demo app that serves two purposes. First, It allows me to demonstrate some of what I've learned in my 3+ years of professional android development. Second, this repo serves as an example [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architected android app anyone can read through to better understand both Android development in Kotlin and the MVVM pattern. It was originally derived from a simple weather application that I built while reading [Antonio Levia's](https://twitter.com/lime_cl) [Kotlin for Android Developers](https://antonioleiva.com/kotlin-android-developers-book/). I took that idea and decided to add more features while also injecting an overall design architecture and redesigning the way everything works together. You'll notice the app is themed like that of Tamriel from the Elder Scrolls series... that part was just for fun! Praise Talos.


Final Product
=================
Here's the gif of the final product. I encourage you to read more about the app's development, but if you just want to see the final form, I totally get it.

<p align="center">
  <img src=https://i.imgur.com/m2IgX5f.gif/>
</p>

To make sense of what your seeing, consult the below tables for how dates in Tamriel work, courtesy of [The Unofficial Elder Scrolls Pages](http://en.uesp.net/wiki/Main_Page).

| Month on Tamriel  | Month on Earth |
| ----------------- |:--------------:|
|Morning Star	      |January         |
|Sun's Dawn	        |February	       |
|First Seed	        |March	         |
|Rain's Hand	      |April	         |
|Second Seed	      |May	           |
|Midyear            |June            |
|Sun's Height      	|July	           |
|Last Seed	        |August	         |
|Hearthfire         |September	     |
|Frostfall          |October	       |
|Sun's Dusk         |November	       |
|Evening Star      	|December        |

| Day of week on Tamriel  | Day of week on Earth |
| ----------------------- |:--------------------:|
|Sundas	                  |Sunday                |
|Morndas	                |Monday                |
|Tirdas	                  |Tuesday               |
|Middas	                  |Wednesday             |
|Turdas	                  |Thursday              |
|Fredas	                  |Friday                |
|Loredas	                |Saturday              |


How To Run The App (Now with Docker!)
=================
The app is not going to be deployed on the google play store for various reasons including licensing (I don't work for Bethesda yet) and API request limits (I don't own the API's being utilized). What this means is that to run the app you'll need to manually build the APK yourself. Luckily you are on the docker-branch, meaning this should be an easy process if you are familiar with Docker. Lets run through the steps for building the app yourself.
1. First, you'll need an install of [Docker](https://www.docker.com/) on your machine. Make sure your docker configuration is set to `windows containers`. 
2. Now `cd` into this directory with your favorite bash terminal and run the script `build-docker-image.sh`. This will setup a linux-based docker image on your machine containing [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), the [Android SDK](https://developer.android.com/studio/), [Gradle](https://gradle.org/), and the Android SDK build/platform [tools](https://developer.android.com/studio/releases/build-tools). Basically, everything you need to compile all of the Kotlin code and build yourself an APK. Note that this will take a while the first time, as it has to download all of the packages within the docker container. This will take a few minutes to download the entire Android SDK.
3. Ensure the process worked by running `docker images`. You should see an image named `android`.
4. Use your favorite bash terminal and run the script `buildapk.sh` from within this directory. This will start the android container, mount the current project directory within the docker container, and use gradle to to build a debug apk. This process will take a few minutes to build the apk, so be patient.
5. You should now have a file called `app-debug.apk` within your local repository directory. Now you can simply file transfer that apk to your prefered device/emulator, or if you have [Adb](LINK) installed, you can simply connect your device, and from within this directory, use the command `adb install` in your given terminal. I highly recommend using adb, as it expedites the uploading process.
6. Enjoy the app!

Note: You may have to change the permision on these bash scripts using `chmod +x filename`

If you want to know more about why I used docker, see the [Why Docker](Link) section of this readme!

An Agile Approach
=================
This project took on a very agile-like workflow. I consistently produced iterations which featured fully functioning products as I worked towards my final deliverable. This iterable approach allowed for faster-developed prototypes and the ability to make changes to my development process with ease. Unfortunately, as the only contributor, I wasn't able to utilize any sort of feature-branch-workflow nor did I write any unit test (which is part of the greatness of the MVVM architecture, more on that later...) I've included gifs/pictures of every "iteration" I developed. Each of these stages can be found by scraping through the commit history of this repo. You can think of the below images as iterations 0-5 in the order they are displayed.

<p align="center">
  <img src=https://i.imgur.com/84gThTs.png/>
  <img src=https://i.imgur.com/uzvuB0Z.gif>
  <img src=https://i.imgur.com/wFJO9qi.gif>
  <img src=https://i.imgur.com/6SzuvV5.gif>
  <img src=https://i.imgur.com/Ed0742u.gif>
  <img src=https://i.imgur.com/m2IgX5f.gif>
</p>


Why MVVM and Architecture Components
=================
I once read a quote in another developers blog post that stuck with me. It basically stated, "You know an app has been thoughtfully designed when something breaks, and as a developer, you know exactly where to go to fix it." That is the **essential** idea behind carefully implementing an application architecture. If you're a longtime android developer, you probably know the pitfalls of developing a project without a clear architectural pattern. Architectural design is relatively new in the Android world and allows us to define how pieces of the app work together/how much they know about other components. The MVVM (model-view-viewmodel) is similar to the more popular [MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) (model-view-controller) or [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) (model-view-presenter) patterns you might find in other areas of software development. With MVVM your [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html) exposes a stream of updates to the view whose only responsibility is to render a given state. Because of this reactive approach, the ViewModel doesn't need a reference to the view like you would need in an MVC/MVP pattern and furthermore all of those ugly interfaces are dropped completely. To further understand MVVM, consider the role of each component

* [Model](https://github.com/justiceadamsUNI/Android-MVVM-Weather-In-Tamriel-App/blob/master/app/src/main/java/weatherintamriel/model/DomainModelClasses.kt)     - the data section of our app. A housing for all data that needs to be manipulated and displayed
* [View](https://github.com/justiceadamsUNI/Android-MVVM-Weather-In-Tamriel-App/blob/master/app/src/main/java/weatherintamriel/view/WeatherListActivity.kt)      - the UI portion of the app. In charge of displaying any events, it observes from the ViewModel.
* [ViewModel](https://github.com/justiceadamsUNI/Android-MVVM-Weather-In-Tamriel-App/blob/master/app/src/main/java/weatherintamriel/viewmodel/WeatherListViewModel.kt) - the connection between the model and the view. Handles communication with repositories for aggregating data into models and telling the view about any updates it needs.

Here's a nice graphic that shows the relationship of each component

<p align="center">
  <img src=https://i.imgur.com/trN0J9V.jpgV>
</p>

The projects source file directory is structured in a way such that it makes it easy to find the code for each component.

```baash
├── src/main/java/weatherintamriel
│   ├── api
│   ├── component
│   ├── **model**
│   ├── module
│   ├── util
│   ├── **view**
│   └── **viewmodel**
```

Android's architecture components provide a nice layer of abstraction over the underlying mechanisms that make the MVVM model work. LiveData and ViewModel, in particular, are a huge help in implementing a system that's **Lifecycle aware**. That's right, no storing and restoring data in a bundle across configuration changes! All of this together gives us a unit testable, encapsulated class of business logic that's easy to read and is decoupled from anything in the Android framework! Here's the simple yet elegant piece of code that makes the app work within the view. Nothing is better than a lightweight activity with little/no business logic. All it does it render what needs to be shown!

```kotlin
    private fun renderState(weatherListViewState: WeatherListViewState) {
        if(weatherListViewState.zipCode == null) {
            if (weatherListViewState.initialState) determineAndShowStartupState()
            else showZipCodeEntryDialog(
                    if (weatherListViewState.showErrorDialog)
                        R.string.error_finding_zip_code_prompt else
                        R.string.enter_zip_code_prompt)
        }

        setProgressSpinnerVisible(weatherListViewState.showingProgressSpinner)

        setWeatherDataVisible(!weatherListViewState.showingProgressSpinner)

        showZipCodeMenuItemEnabled(!weatherListViewState.showingProgressSpinner)

        weatherListViewState.locationInfo?.let{ showToastWithLocationInfo(it) }

        val controller = WeatherListEpoxyController()
        forecast_list.adapter = controller.adapter
        controller.setData(weatherListViewState.forecasts, weatherListViewState.currentWeather)
    }
```


Why Dagger
=================
[Dagger](https://google.github.io/dagger/) is a static dependency injection framework that allows for easily creating compositional software modules. Dagger allowed me to focus on the main classes of the project and then simply define how they are related. This alleviated the job of creating each object from within other objects, thus allowing me to simplify classes by removing factory-like instantiation methods. Dagger generates and handles the code for tieing everything together.


Why RxJava
=================
[RxJava](https://github.com/ReactiveX/RxJava)/[RxAndroid](https://github.com/ReactiveX/RxAndroid) allows us to use an event-driven reactive style of programming for handling our network interactions. This is extremely nice as it works just like the ViewModel itself works (it exposes a stream of events). With RxJava, we get the simplicity of handling asynchronous network calls with ease and responding to associated events within the stream. Take a look at the following piece of code from the ViewModel which is extremely easy to read/understand.

```kotlin
   private fun getForecast(zipCode: Int){
        weatherRepository.getForecasts(zipCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { forecastResultToForecastModelMapper.convertToListOfModels(it) }
                .subscribe(::showForecasts, ::showErrorDialog)
    }
```
It's easy to see what's happening. We get the data from the repository off the main thread, map it to the correct models on the main thread, then update the viewstate which will notify the view that a change has been made within its own stream of updates.


Why Epoxy
=================
[Epoxy](https://github.com/airbnb/epoxy) is Airbnb's library which allows us to create a complex recyclerview. It uses its own MVC architecture where you define a controller to build in the recycler view, each of which handles their own rendering. This allows us to create a RecyclerView whose adapter has multiple types of viewholders. You'll notice that the first element of the weather list has a different layout from every other element (the forecast). We can do this with epoxy without having to write an extension to the RecyclerView.Adapter. Pretty slick!


Why Docker
=================
[Docker](https://www.docker.com/) is a very valuable tool which allows us to bundle our application and its dependencies into what's known as a container. By using docker, I can enusure that everyone with docker installed on their machine has the ability to build the APK for their own use. Docker makes it so that you won't have any errors building the APK (If you chose to do so via docker).


Aditional reading on Android MVVM
=================
* [Why app architecture matters](https://learnappmaking.com/why-app-architecture-matters/)
* [Android Architecture Components — now with 100% more MVVM](https://android.jlelse.eu/android-architecture-components-now-with-100-more-mvvm-11629a630125)
* [Model–view–viewmodel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)
* [Simple Android MVVM using RX and Kotlin](https://medium.com/corebuild-software/simple-android-mvvm-using-rx-and-kotlin-9769a91b03ef)
* [Android architecture's with examples](https://github.com/googlesamples/android-architecture)


Questions
=================
If you have any questions about the repo/how to build your own MVVM architected app, feel free to reach out to me with any questions/comments/concerns!

Resources That Made This Project Possible
=================
### Android Libraries
* [Epoxy](https://github.com/airbnb/epoxy) - Epoxy is an Android library for building complex screens in a RecyclerView 
* [Dagger](https://github.com/google/dagger) - A fast dependency injector for Android and Java.
* [RxAndroid](https://github.com/ReactiveX/RxAndroid) - RxJava bindings for Android
* [RxKotlin](https://github.com/ReactiveX/RxKotlin) - RxJava bindings for Kotlin
* [Retrofit](https://github.com/square/retrofit) - Type-safe HTTP client for Android and Java by Square, Inc. 
* [Picasso](https://github.com/square/picasso) - A powerful image downloading and caching library for Android 
* [Anko](https://github.com/Kotlin/anko) - Pleasant Android application development. Used for accessing thread schedulers
* [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back

### Other
* [Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) - A collection of libraries that help you design robust, testable, and maintainable apps
* [Imgur](https://imgur.com/) - Image hosting website, used to host images for this readme
* [OpenWeatherMap API](https://openweathermap.org/api) - Simple and fast and free weather API from OpenWeatherMap you have access to current weather data/forecast
* [Zipcode api](https://www.zipcodeapi.com/) - Used to access zip code location information from user entered zip
* [UESP.net](http://en.uesp.net/wiki/Main_Page) - Used to download all icons from the oblivion repository and access information about tamriel
* [Firebase](https://firebase.google.com/) - Used to house images for icons to load with picasso
* [Photoshop](www.adobe.com/Photoshop) - The world’s best imaging and design app .Used to create images for site. Used to create luancher icon and adjust size of weather icons.
* [Android Studio](https://developer.android.com/studio/index.html) - Android Studio provides the fastest tools for building apps on every type of Android device
* [Github](https://github.com/) - GitHub is a web-based Git or version control repository and Internet hosting service
* [EZGif](https://ezgif.com/) - Online GIF maker and image editor. Used to create gifs from mp4's for this readme
