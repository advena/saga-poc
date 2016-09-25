package validity.period.change.command;

import lombok.AllArgsConstructor;

import java.time.YearMonth;

/**
 * Created by advena on 25.09.16.
 */
@AllArgsConstructor
public class ValidityPeriodChange {
    private final YearMonth start;
    private final YearMonth end;

}
