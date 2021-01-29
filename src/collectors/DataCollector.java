package cs.binghamton.edu.collectors;

import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

public abstract class DataCollector {
  private static final ThreadFactory threadFactory = r -> new Thread(r);

  private final Duration period;
  private final ArrayList<Instant> data = new ArrayList<>();
  protected final ScheduledExecutorService executor;

  private boolean isRunning = false;

  public DataCollector(int workerCount, Duration period) {
    this.executor = newScheduledThreadPool(workerCount, threadFactory);
    this.period = period;
  }

  public final void start() {
    isRunning = true;
    executor.submit(() -> runAndReschedule(() -> data.add(Instant.now()), period));
  }

  public final void stop() {
    isRunning = false;
    executor.shutdown();
    try {
      executor.awaitTermination(100, MILLISECONDS);
    } catch (Exception e) {
      System.out.println("could not terminate");
      e.printStackTrace();
    }
  }

  public final List<Instant> read() {
    return data;
  }

  /** Execute the runnable at the prescribed time. */
  protected abstract void schedule(Runnable r, Duration scheduledTime);

  private void runAndReschedule(Runnable r, Duration period) {
    if (!isRunning) {
      return;
    }

    Instant start = Instant.now();
    r.run();
    Duration scheduleTime = period.minus(Duration.between(start, Instant.now()));

    schedule(() -> runAndReschedule(r, period), scheduleTime);
  }

  private static double diff(List<Instant> timestamps) {
    double diff = 0;
    for (int i = 0; i < timestamps.size() - 1; i++) {
      diff += Duration.between(timestamps.get(i), timestamps.get(i + 1)).toMillis();
    }
    return diff / timestamps.size();
  }
}
