package mk.ukim.finki.emt.billingpayment.domain.valueObjects;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class Amount {
    private BigDecimal value;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    public Amount() {
    }

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

    public Currency getCurrency() {
        return currency;
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

    @Override
    public String toString() {
        return String.format("%s %s", currency, value);
    }

    public Amount add(Amount other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add amounts with different currencies");
        }
        return new Amount(this.value.add(other.value), this.currency);
    }
}
