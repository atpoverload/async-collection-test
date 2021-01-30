package cs.binghamton.edu.collectors;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public final class SleepingDataCollector extends DataCollector {
  public SleepingDataCollector(int workerCount, Duration period) {
    super(workerCount, period);
  }

  @Override
  protected void schedule(Runnable r, Duration scheduledTime) {
    try {
      TimeUnit.NANOSECONDS.sleep(scheduledTime.toNanos());
      executor.execute(r);
    } catch (Exception e) {
    }
  }
}
