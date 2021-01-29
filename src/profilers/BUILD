load("@rules_java//java:defs.bzl", "java_library")

java_library(
  name = "result",
  visibility = ["//visibility:public"],
  srcs = ["CollectorResult.java"],
  deps = [
    "@org_openjdk_jmh_jmh_core",
  ]
)

java_library(
  name = "profilers",
  visibility = ["//visibility:public"],
  srcs = glob(["*Profiler.java"]),
  deps = [
    ":result",
    "//src/collectors",
    "@org_openjdk_jmh_jmh_core",
  ]
)
