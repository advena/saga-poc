package validity.period.change;

import validity.period.aggregate.ValidityPeriodAggregate;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by advena on 25.09.16.
 */

public class EventsAssert implements ValidityPeriodAggregate.Events{
    private List<List<YearMonth>> periods = new ArrayList<>();

    @Override
    public void emit(ValidityPeriodAggregate.ValidityPeriodChanged validityPeriodChanged) {
        this.periods.add(validityPeriodChanged.getExtendedMonths());
    }

    public List<YearMonth> getMonths() {
        return periods.get(periods.size() - 1);
    }
}
