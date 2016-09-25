package validity.period.aggregate;

import common.Event;
import lombok.AllArgsConstructor;
import lombok.Value;
import validity.period.ValidityPeriod;
import validity.period.command.ValidityPeriodChange;

import java.time.YearMonth;
import java.util.List;

/**
 * Created by advena on 23.09.16.
 */
@AllArgsConstructor
public class ValidityPeriodAggregate {
    private final long gameId;
    private ValidityPeriod validityPeriod;
    private final Events events;

    public void changePeriod(ValidityPeriodChange validityPeriod) {
        YearMonth start = validityPeriod.getStart();
        YearMonth end = validityPeriod.getEnd();
        if(areValid(start, end)) {
            ValidityPeriod newValidityPeriod  = new ValidityPeriod(start, end);
            List<YearMonth> availibilityMonths = newValidityPeriod.getAllMonths();
            ValidityPeriodChanged event = new ValidityPeriodChanged(availibilityMonths);
            handle(newValidityPeriod);
            events.emit(event);
        }

    }

    private void handle(ValidityPeriod newValidityPeriod) {

    }

    private boolean areValid(YearMonth start, YearMonth end) {
        return true;
    }


    public interface Events {
        void emit(ValidityPeriodChanged validityPeriodChanged);
    }

    @Value
    public static class ValidityPeriodChanged implements Event{
        private final List<YearMonth> extendedMonths;
    }
}
