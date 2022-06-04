package com.yakub.pay.payment.core.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Id id;
    private User from;
    private User to;
    private BigDecimal amount;
    private State state;
    private Date createdDate;
    private Date updatedDate;

    public Order(Id id, User from, User to, BigDecimal amount, State state, Date createdDate, Date updatedDate) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.state = state;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Id getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public State getState() {
        return state;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", amount=" + amount +
                ", state=" + state +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
