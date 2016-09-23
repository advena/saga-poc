package payment.data;

import lombok.Value;

import java.math.BigDecimal;

/**
 * Created by advena on 24.09.16.
 */
@Value
public class PaymentDto {

    private final BigDecimal price;
}
