load("@rules_java//java:defs.bzl", "java_library")

java_plugin(
    name = "collection_test_jmh_annotation_processor",
    deps = ["@org_openjdk_jmh_jmh_generator_annprocess"],
    processor_class = "org.openjdk.jmh.generators.BenchmarkProcessor",
    visibility = ["//visibility:private"],
)

java_binary(
  name = "scheduling_benchmark",
  visibility = ["//visibility:public"],
  main_class = "cs.binghamton.edu.profilers.SchedulingProfiler",
  srcs = ["CollectionBenchmark.java"],
  deps = [
    "//src/collectors",
    "//src/profilers",
    "@org_openjdk_jmh_jmh_core",
  ],
  plugins = [":collection_test_jmh_annotation_processor"]
)

java_binary(
  name = "sleeping_benchmark",
  visibility = ["//visibility:public"],
  main_class = "cs.binghamton.edu.profilers.SleepingProfiler",
  srcs = ["CollectionBenchmark.java"],
  deps = [
    "//src/collectors",
    "//src/profilers",
    "@org_openjdk_jmh_jmh_core",
  ],
  plugins = [":collection_test_jmh_annotation_processor"]
)

java_binary(
  name = "parking_benchmark",
  visibility = ["//visibility:public"],
  main_class = "cs.binghamton.edu.profilers.ParkingProfiler",
  srcs = ["CollectionBenchmark.java"],
  deps = [
    "//src/collectors",
    "//src/profilers",
    "@org_openjdk_jmh_jmh_core",
  ],
  plugins = [":collection_test_jmh_annotation_processor"]
)

java_binary(
  name = "spinning_benchmark",
  visibility = ["//visibility:public"],
  main_class = "cs.binghamton.edu.profilers.SpinningProfiler",
  srcs = ["CollectionBenchmark.java"],
  deps = [
    "//src/collectors",
    "//src/profilers",
    "@org_openjdk_jmh_jmh_core",
  ],
  plugins = [":collection_test_jmh_annotation_processor"]
)
