# Ktor with Koin

An example that demonstrates a failing Ktor with Koin that produces the error:
> org.koin.core.error.KoinAppAlreadyStartedException: A Koin Application has already been started

## Running

To run the sample, execute the following command in a repository's root directory:

```bash
./gradlew clean test
```

Everything that I want to achieve can be found inside `BaseApplicationTest` and `ApplicationTest`.
