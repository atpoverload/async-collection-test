#!/bin/bash

periods=(1000000 500000 100000 50000)
for period in "${periods[@]}"; do
  bazel run src:scheduling_benchmark --jvmopt="-Dcollector.period=$period"
  bazel run src:sleeping_benchmark --jvmopt="-Dcollector.period=$period"
  bazel run src:parking_benchmark --jvmopt="-Dcollector.period=$period"
  bazel run src:spinning_benchmark --jvmopt="-Dcollector.period=$period"
done
