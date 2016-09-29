package payment.aggregate;

import lombok.AllArgsConstructor;
import lombok.Value;
import payment.command.GeneratePayments;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by advena on 26.09.16.
 */
@AllArgsConstructor
public class PaymentAggregate {
    private final long userId;
    private Events events;

    public void generatePayments(GeneratePayments generatePayments) {
        if(valid(generatePayments)) {
            handle(generatePayments);
            List<Payment> payments = generateListOfPayments(generatePayments);
            BigDecimal summaryPrice = calculatePrice(generatePayments);
            events.emit(new PaymentsGenerated(this.userId, payments, summaryPrice));
        }
    }

    private BigDecimal calculatePrice(GeneratePayments generatePayments) {
        return generatePayments.getPrice().multiply(BigDecimal.valueOf(generatePayments.getMonths().size()));
    }

    private List<Payment> generateListOfPayments(GeneratePayments generatePayments) {
        List<Payment> payments = new ArrayList<>();
        generatePayments.getMonths().forEach(yearMonth -> payments.add(new Payment(yearMonth, this.userId, generatePayments.getPrice())));
        return payments;
    }

    private void handle(GeneratePayments generatePayments) {

    }

    private boolean valid(GeneratePayments generatePayments) {
        return true;
    }

    public interface Events {

        void emit(PaymentsGenerated paymentsGenerated);

    }

    @Value
    public class PaymentsGenerated {
        private final long userId;
        private final List<Payment> payments;
        private final BigDecimal summaryPrice;
    }

    @Value
    public class Payment {
        private final YearMonth date;
        private final long userId;
        private final BigDecimal price;
    }
}
