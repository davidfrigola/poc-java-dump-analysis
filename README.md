# poc-java-dump-analysis

Some code samples to force errors - and then generate memory/thread dumps to analyse

[![Build Status](https://travis-ci.com/davidfrigola/poc-java-dump-analysis.svg?branch=master)](https://travis-ci.com/davidfrigola/poc-java-dump-analysis)

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

Use `make heapdump-ci` when using the ci (dockerized)` version.This will generate a hprof file in your `/tmp` folder.
Note : be sure to chmod the resulting `/tmp/heapdump.hprof` file so your user can read it.


# Exploring the threads

Use `make load-thread` up to 3 times. This will use doThread endpoint, creating 1000 threads sleeping 1 minute each.
Then, obtain thread dumps using `make threaddump`.
This will generate `threaddump_[1..5].tdump` files you can analyse later.

Nice online tool : https://jstack.review/ you can upload multiple thread dump files, and compare in pairs.

Use `make threaddump-ci` when using the ci version. This will generate a `.tdump` file in your tmp folder.

# CI

Travis-ci is used to demonstrate how this all works.
It will use `make ci` , that includes:

* build app and docker container
* start container (naugthy slepp to wait for it to start, but hey, this works!)
* hit the `doStore` endpoint several times so there's some heap info available
* generate heap and thread dumps
* check (`ls` to the rescue) dump files are generated in `/tmp` folder

You can use `make ci` locally to quickly get some dumps to explore.


