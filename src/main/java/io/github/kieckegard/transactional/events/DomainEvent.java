/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events;

import java.time.LocalDateTime;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class DomainEvent<T> {
    
    private String id;
    private String aggregateId;
    private T payload; // should be JSONB
    private LocalDateTime createdAt;
    private LocalDateTime handledAt;
    
    public boolean isUnhandled() {
        return this.handledAt == null;
    }

    public DomainEvent(final String aggregateId, T payload) {
        this.createdAt = LocalDateTime.now();
        this.handledAt = null;
        
        this.aggregateId = aggregateId;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public T getPayload() {
        return payload;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getHandledAt() {
        return handledAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHandledAt(LocalDateTime handledAt) {
        this.handledAt = handledAt;
    }

    @Override
    public String toString() {
        return "DomainEvent{" + "id=" + id + ", aggregateId=" + aggregateId + ", payload=" + payload + ", createdAt=" + createdAt + ", handledAt=" + handledAt + '}';
    }
}
