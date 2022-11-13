# Popular-Movies
Simple app display popular movies from The movie db public API "https://developers.themoviedb.org/3/getting-started/introduction"
#Architecture
Clean architecture based on MVVM (Model-View-ViewModel) with Kotlin Coroutines and Flow

The following diagram shows all the layers and how each layer interacts with each other. This architecture uses a layered software architecture.
![alt text](https://github.com/mhelmi/WeatherForecast/blob/master/art/mvvm_architecture.png)

Built With 🛠

Kotlin - official programming language for Android development.
Coroutines - for asynchronous or non-blocking programming.
Flow - a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
Android Architecture Components - Part of Jetpack it's a set of libraries that help you design robust, testable, and maintainable apps.
LiveData - an observable data holder class.
ViewModel - store and manage UI-related data in a lifecycle conscious way.
Room - persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
Navigation - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app.
Hilt - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection (Based on Dagger 2).
Retrofit - A type-safe HTTP client for Android.
Gson A Java serialization/deserialization library to convert Java Objects into JSON and back.
Material Design are interactive building blocks for creating a friendly user interface.
Glide An image loading and caching library.
