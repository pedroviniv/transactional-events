/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events.demo;

import io.github.kieckegard.transactional.events.DomainEvent;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class CreatedOrderEvent extends DomainEvent<Order> {
    
    public CreatedOrderEvent(String aggregateId, Order payload) {
        super(aggregateId, payload);
    }
    
}
