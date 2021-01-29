package cs.binghamton.edu.profilers;

import static org.openjdk.jmh.results.AggregationPolicy.AVG;
import static org.openjdk.jmh.results.Defaults.PREFIX;
import static org.openjdk.jmh.results.ResultRole.SECONDARY;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.LongStream;
import org.openjdk.jmh.results.Aggregator;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.util.ListStatistics;

/** A result that reports the number of collected vs expected samples from a trial. */
class CollectorResult extends Result<CollectorResult> {
  private final long[] values;

  public CollectorResult(long[] values) {
    super(SECONDARY, PREFIX + "collector", new ListStatistics(values), "ns", AVG);
    this.values = values;
  }

  @Override
  protected Aggregator<CollectorResult> getThreadAggregator() {
    return new CollectorResultAggregator();
  }

  @Override
  protected Aggregator<CollectorResult> getIterationAggregator() {
    return new CollectorResultAggregator();
  }

  private class CollectorResultAggregator implements Aggregator<CollectorResult> {
    @Override
    public CollectorResult aggregate(Collection<CollectorResult> results) {
      LongStream valueStream = Arrays.stream(values);
      for (CollectorResult r : results) {
        valueStream = LongStream.concat(valueStream, Arrays.stream(r.values));
      }
      return new CollectorResult(valueStream.toArray());
    }
  }
}
