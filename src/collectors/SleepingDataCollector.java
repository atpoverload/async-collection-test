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
      // if (scheduledTime.toMillis() > 0) {
      //   TimeUnit.MILLISECONDS.sleep(scheduledTime.toMillis());
      // }
      // if (scheduledTime.toNanos() > 0) {
      //   TimeUnit.NANOSECONDS.sleep(Math.min(scheduledTime.getNano(), 999999));
      // }
      executor.execute(r);
    } catch (Exception e) {
    }
  }
}
