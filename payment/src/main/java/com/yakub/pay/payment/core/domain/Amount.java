package com.yakub.pay.payment.core.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Amount {
    private BigDecimal amount;
    private List<BigDecimal> fees;

    public Amount(BigDecimal amount, List<BigDecimal> fees) {
        this.amount = amount;
        this.fees = fees;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<BigDecimal> getFees() {
        return Collections.unmodifiableList(fees);
    }
}
