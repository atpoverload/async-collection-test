#!/bin/bash

periods=(4000000 1000000 500000 100000 50000 1)
for period in "${periods[@]}"; do
  bazel run src:scheduling_benchmark --jvmopt="-Dcollector.period=$period"
  bazel run src:sleeping_benchmark --jvmopt="-Dcollector.period=$period"
  bazel run src:parking_benchmark --jvmopt="-Dcollector.period=$period"
  bazel run src:spinning_benchmark --jvmopt="-Dcollector.period=$period"
done
