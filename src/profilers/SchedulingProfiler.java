package cs.binghamton.edu.profilers;

import cs.binghamton.edu.collectors.DataCollector;
import cs.binghamton.edu.collectors.SchedulingDataCollector;
import java.time.Duration;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public final class SchedulingProfiler extends CollectorProfiler {
  @Override
  protected DataCollector newDataCollector(int workerCount, Duration period) {
    return new SchedulingDataCollector(workerCount, period);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().addProfiler(SchedulingProfiler.class).build();
    new Runner(opt).run();
  }
}
