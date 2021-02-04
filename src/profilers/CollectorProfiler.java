package cs.binghamton.edu.profilers;

import cs.binghamton.edu.collectors.DataCollector;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.profile.ExternalProfiler;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.Result;

public abstract class CollectorProfiler implements ExternalProfiler {
  private static final int workers = Integer.parseInt(System.getProperty("collector.workers", "1"));
  private static final Duration period =
      Duration.ofNanos(Long.parseLong(System.getProperty("collector.period", "1000000")));
  private DataCollector collector;

  public CollectorProfiler() {}

  protected abstract DataCollector newDataCollector(int workerCount, Duration period);

  /** Starts the clerk. */
  @Override
  public final void beforeTrial(BenchmarkParams benchmarkParams) {
    collector = newDataCollector(workers, period);
    collector.start();
  }

  /** Stops the clerk. */
  @Override
  public final Collection<? extends Result> afterTrial(
      BenchmarkResult br, long pid, File stdOut, File stdErr) {
    collector.stop();
    List<Instant> data = collector.read();
    collector = null;
    return List.of(new CollectorResult(diffs(data)));
  }

  // Default implementations for ExternalProfiler interface
  @Override
  public String getDescription() {
    return getClass().getSimpleName();
  }

  @Override
  public Collection<String> addJVMInvokeOptions(BenchmarkParams params) {
    return Collections.emptyList();
  }

  @Override
  public Collection<String> addJVMOptions(BenchmarkParams params) {
    return Collections.emptyList();
  }

  @Override
  public boolean allowPrintOut() {
    return true;
  }

  @Override
  public boolean allowPrintErr() {
    return false;
  }

  private static long[] diffs(List<Instant> timestamps) {
    Collections.sort(timestamps);
    long[] diffs = new long[timestamps.size() - 1];
    for (int i = 0; i < timestamps.size() - 1; i++) {
      diffs[i] = Duration.between(timestamps.get(i), timestamps.get(i + 1)).toNanos();
    }
    return diffs;
  }
}
