# poc-java-dump-analysis

Some code samples to force errors - and then generate memory/thread dumps to analyse


# Application

A simple Spring Boot REST App is provided with two endpoints
* doStore
* doThread

You can start the application by running `make build` then `make run`

** This requires java sdk 1.8 and mvn installed.

## doStore

This endpoint adds a `big` map to in memory storage. Use `make load-heap` to run it.


## doThread

This endpoint generates a big number of threads, sleeping for 10 minutes. Use `make load-thread` to run it.
Running it a couple of times will provide enough number of threads!

# Exploring the heap

TODO

# Exploring the threads

TODO

