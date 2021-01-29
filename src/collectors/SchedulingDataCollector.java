package cs.binghamton.edu.collectors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.time.Duration;

public final class SchedulingDataCollector extends DataCollector {
  public SchedulingDataCollector(int workerCount, Duration period) {
    super(workerCount, period);
  }

  @Override
  protected void schedule(Runnable r, Duration scheduledTime) {
    if (scheduledTime.toMillis() > 0) {
      executor.schedule(r, scheduledTime.toMillis(), MILLISECONDS);
    } else if (scheduledTime.toNanos() > 0) {
      executor.schedule(r, scheduledTime.toNanos(), NANOSECONDS);
    } else {
      executor.submit(r);
    }
  }
}
