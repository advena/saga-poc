package validity.period;

import lombok.AllArgsConstructor;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by advena on 23.09.16.
 */
@AllArgsConstructor
public class ValidityPeriod {
    private final YearMonth start;
    private final YearMonth end;

    public List<YearMonth> getAllMonths() {
        List<YearMonth> months = new ArrayList<>();
        YearMonth currentMonth = start;
        while (!matchesEnd(currentMonth)) {
            months.add(currentMonth);
            currentMonth = currentMonth.plusMonths(1);
        }
        return months;
    }

    private boolean matchesEnd(YearMonth month) {
        return !(month.isBefore(end) || month.equals(end));
    }
}
