package com.yakub.pay.payment.core.model;

import java.util.Collections;
import java.util.List;

public class PaymentRequest {

    private String clientTransactionId;
    private List<String> orderIds;
    private String createdDate;

    public PaymentRequest(String clientTransactionId, List<String> orderIds, String createdDate) {
        this.clientTransactionId = clientTransactionId;
        this.orderIds = orderIds;
        this.createdDate = createdDate;
    }

    public String getClientTransactionId() {
        return clientTransactionId;
    }

    public List<String> getOrderIds() {
        return Collections.unmodifiableList(orderIds);
    }

    public String getCreatedDate() {
        return createdDate;
    }
}
