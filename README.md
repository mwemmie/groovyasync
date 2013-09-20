groovyasync
===========

Groovy async

Goal - Want as minimal, clean syntax as possible to execute a block of managers asynchronously, wait for all their executions to complete, and then easily be able to get and work with the results.

Setup - Created 3 dummy managers, each with a unique domain object type, each which has a simulated amount of time to execute

Examples
=========
GparsAsyncLibraryRunner.groovy - Utilizes the Gpars groovy concurrency library.  Test proves that we can get very clean, concise concurrent code
StandardGroovyAsyncRunner.groovy - Mixes some Java with Groovy to show how the standard groovy library can do Closure coercion which can reduce verbosity of anonymous inner classes
AsyncThreadManagerRunner.groovy - Best attempt at writing as concise native concurrent groovy.  Better than Java but not nearly as clean as Gpars.
