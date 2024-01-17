An Android app that presents a list of listings using data from the provided JSON source, written 100% in Kotlin and Jetpack Compose using Android Jetpack Components.
## Prerequisites
To run the project in your local environment, you need
* Go to the android studio and create a new project using the 'Get from version control' option.
* Paste this link `https://github.com/daniel-waiguru/TripitacaAndroid.git`
* Add your Google maps API Key to `local.properties` file or copy paste the one provided below

  ```
  MAPS_API_KEY=AIzaSyCGk4OKjMi7O5Rlg7zU9q_xY5e-ROzxIxI
  ```
* Build the project and run it
* Ready app APK can be downloaded from [here](https://drive.google.com/file/d/1rh8OYvwXhjMizEKS4GIZkyMwHMD-y0fo/view?usp=sharing)
* N/B: Please note and notify your team that the input fields for sign-in by email and password are for aesthetic purposes only, and the feature is not yet implemented as there is no login API.

## Tech-stack
* Tech-stack
    * [Kotlin](https://kotlinlang.org/) - a modern, cross-platform, statically typed, general-purpose programming language with type inference.
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - lightweight threads to perform asynchronous tasks.
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - a stream of data that emits multiple values sequentially.
    * [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#:~:text=StateFlow%20is%20a%20state%2Dholder,property%20of%20the%20MutableStateFlow%20class.) - Flow APIs that enable flows to emit updated state and emit values to multiple consumers optimally.
    * [Dagger Hilt](https://dagger.dev/hilt/) - a dependency injection library for Android built on top of [Dagger](https://dagger.dev/) that reduces the boilerplate of doing manual injection.
    * [Gson](https://github.com/google/gson) A Java and Kotlin serialization/deserialization library to convert Kotlin/Java Objects into JSON and back
    * [Jetpack](https://developer.android.com/jetpack)
        * [Jetpack Compose](https://developer.android.com/jetpack/compose) - A modern toolkit for building native Android UI
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform actions in response to a change in the lifecycle state.
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data lifecycle in a conscious manner and survive configuration change.
        * [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - A data storage solution backed using Kotlin Coroutines and Flows that allows you to store key-value pairs or typed objects with protocol buffers
    * [Timber](https://github.com/JakeWharton/timber) - a highly extensible Android logger.

* Architecture
    * MVVM—Model View ViewModel pattern with Feature-based modularization. Note that this can be modified to include a domain layer for each feature depending on the business logic needed to be handled.
  
  <img src="/docs/tripitaca_arch.png" />
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - An alternative syntax for writing Gradle build scripts using Koltin.
    * [Version Catalogs](https://developer.android.com/build/migrate-to-catalogs) - A scalable way of maintaining dependencies and plugins in a multi-module project.
    * [Convention Plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html) - A way to encapsulate and reuse common build configuration in Gradle, see [here](https://github.com/daniel-waiguru/TripitacaAndroid/tree/main/build-logic/convention/src/main/java)
* CI/CD
    * [GitHub Actions](https://github.com/features/actions)
 
## Dependencies

All the dependencies (external libraries) are managed using version catalogs and defined in a single place `gradle/libs.versions.toml` file. This is a scalable approach to manage dependencies and use the same dependency version across all modules.

## Code Analysis
This repo uses Android Studio's built-in linter to analyze the codebase and identify potential code style violations, code quality issues, etc.

```shell script
./gradlew lintDebug
```

## App Screenshots
| Sign In               | Properties            | Properties Filtered  |
| -------------         |:--------------------: | -------------------: |
| <img src="/docs/sign_in.png" width="260"/>     | <img src="/docs/properties.png" width="260"/>      | <img src="/docs/properties_filtered.png" width="260"/> |

| Property Details | Property Details Scrolled |
| -----------------:| ------------------------:|
| <img src="/docs/property_info1.png" width="260"/> | <img src="/docs/property_info2.png" width="260"/>          |

## App Recording 🎥

https://github.com/daniel-waiguru/TripitacaAndroid/assets/52042764/537a0af0-c3e4-4288-b1e4-f836ab1d1803


