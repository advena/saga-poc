package saga;

import payment.aggregate.PaymentAggregate;
import saga.command.ValidityPeriodExtensionCommand;
import user.aggregate.UserAggregate;
import validity.period.aggregate.ValidityPeriodAggregate;

/**
 * Created by advena on 23.09.16.
 */
public class ValidityExtensionSaga {

    private final UserAggregate userAggregate;
    private final ValidityPeriodAggregate validityPeriodAggregate;
    private final PaymentAggregate paymentAggregate;

    private ValidityExtensionSaga(UserAggregate userAggregate, ValidityPeriodAggregate validityPeriodAggregate, PaymentAggregate paymentAggregate) {
        this.userAggregate = userAggregate;
        this.validityPeriodAggregate = validityPeriodAggregate;
        this.paymentAggregate = paymentAggregate;
    }
    public static ValidityExtensionSaga generateSaga(UserAggregate userAggregate, ValidityPeriodAggregate validityPeriodAggregate, PaymentAggregate paymentAggregate) {
        return new ValidityExtensionSaga(userAggregate, validityPeriodAggregate, paymentAggregate);
    }

    public void trigger(ValidityPeriodExtensionCommand extensionCommand) {
    }
}
