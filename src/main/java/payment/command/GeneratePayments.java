package payment.command;

import lombok.Value;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

/**
 * Created by advena on 26.09.16.
 */
@Value
public class GeneratePayments {
    private final long gameId;
    private final List<YearMonth> months;
    private final BigDecimal price;
}
