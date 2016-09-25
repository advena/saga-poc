package validity.period.command;

import common.Command;
import lombok.Value;

import java.time.YearMonth;

/**
 * Created by advena on 25.09.16.
 */
@Value
public class ValidityPeriodChange implements Command{
    private final YearMonth start;
    private final YearMonth end;

}
