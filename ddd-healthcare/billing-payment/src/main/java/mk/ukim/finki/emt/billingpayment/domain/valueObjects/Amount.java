package mk.ukim.finki.emt.billingpayment.domain.valueObjects;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public class Amount {
    private final BigDecimal value;
    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    public Amount(BigDecimal value, Currency currency) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency must be provided");
        }
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value.equals(amount.value) && currency.equals(amount.currency);
    }

    @Override
    public int hashCode() {
        return value.hashCode() + currency.hashCode();
    }

   public static Amount of(BigDecimal value, Currency currency) {
        return new Amount(value, currency);
    }
}