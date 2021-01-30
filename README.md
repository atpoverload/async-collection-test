# async-collection-test

A set of tests to profile different steady-state async execution implementations in `Java`. [bazel](https://docs.bazel.build/versions/4.0.0/install.html) is required to run this project. Note that the implementations use an `Executor` to drive the periodic execution instead of a loop. The tests could be implemented with loops if we believe there is a performance difference.

The following implementations are exercised:
 - Spinning: System.nanoTime()
 - Sleeping: Thread.sleep
 - Scheduling: ScheduledExecutorService.schedule
 - Parking: LockSupport.parkNanos

Note that the recommended way to manage asynchronous processes in `Java` is to use the `Executor` and `Future` interfaces. This is limited by `Java`, which is on the order of 500 microseconds. If you need a faster period, it's possible your implementation should live in `c` instead.
