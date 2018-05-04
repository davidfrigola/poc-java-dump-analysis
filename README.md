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

This endpoint generates a big number (1000) of threads, sleeping for 1 minutes. Use `make load-thread` to run it.
Running it a couple of times will provide enough number of threads!

# Exploring the heap

Use `make load-heap` up to 5 times. This will use doStore endpoint, storing a relatively big amount of data in memory. Then, to generate a heap dump, use `make heapdump`
This will generate a `heapdump.hprof` file that an be analysed by your favourite tool.

# Exploring the threads

Use `make load-thread` up to 3 times. This will use doThread endpoint, creating 1000 threads sleeping 1 minute each.
Then, obtain thread dumps using `make threaddump`.
This will generate `threaddump_[1..5].tdump` files you can analyse later.

