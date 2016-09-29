package payment;

import payment.aggregate.PaymentAggregate;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by advena on 26.09.16.
 */
public class EventsAssert implements PaymentAggregate.Events {

    List<PaymentAggregate.PaymentsGenerated> payments = new ArrayList<>();

    @Override
    public void emit(PaymentAggregate.PaymentsGenerated paymentsGenerated) {
        payments.add(paymentsGenerated);
    }


    public void assertPaymentsMatches(List<YearMonth> monthsForPayments) {
        payments.get(0).getPayments().stream().anyMatch(paymentYearMonth -> monthsForPayments.contains(paymentYearMonth));
    }

    public void assertNumberOfPaymentsMatches(Integer paymentNumber) {
        assertThat(payments.get(payments.size() - 1).getPayments().size()).isEqualTo(paymentNumber);
    }

    public void assertPaymentPriceMatches(BigDecimal price) {
        payments.get(0).getPayments().stream().allMatch(p -> p.getPrice().equals(price));
    }
}
