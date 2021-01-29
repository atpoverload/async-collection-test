package cs.binghamton.edu.collectors;

import static java.util.concurrent.locks.LockSupport.parkNanos;

import java.time.Duration;

public final class ParkingDataCollector extends DataCollector {
  public ParkingDataCollector(int workerCount, Duration period) {
    super(workerCount, period);
  }

  @Override
  protected void schedule(Runnable r, Duration scheduledTime) {
    parkNanos(scheduledTime.toNanos());
    executor.execute(r);
  }
}
