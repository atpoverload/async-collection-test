package cs.binghamton.edu.collectors;

import java.time.Duration;

public final class SpinningDataCollector extends DataCollector {
  public SpinningDataCollector(int workerCount, Duration period) {
    super(workerCount, period);
  }

  @Override
  protected void schedule(Runnable r, Duration scheduledTime) {
    long start = System.nanoTime();
    while (System.nanoTime() - start < scheduledTime.toNanos()) {
      Thread.onSpinWait();
    }
    executor.execute(r);
  }
}
