package payment.data;

import lombok.Value;

import java.util.List;

/**
 * Created by advena on 23.09.16.
 */
@Value
public class PaymementsDataDto {

    private final List<PaymentDto> payments;
}
