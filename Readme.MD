# PostsDemo app

This Android app shows a list of posts from a sample JSONPlaceholder REST API.

I architected the app following the MVP pattern, having a presenter layer that serves the data from the Domain (API) to the view. 

The app is unit tested using JUnit. I tested the presentation layer of the MVP in isolation, mocking out the dependencies and third party libraries like ´Retrofit´ using Mockito.

Also used 3rd party libraries like Retrofit2 and Butterknife.

## How to run

To run the tests: `./gradlew test`

To build and assemble the APK: `./gradlew assemble`. The apk file can be found at `app/build/outputs/apk`

## Future improvements

- Add dependency injection such as Dagger2. That would ease testing as well.
- Add RxJava for parallel async calls to the API 
    - Some cases require parallelisation such as retrieving user info and comments at once.
- Add further testing, intrumentation, etc.