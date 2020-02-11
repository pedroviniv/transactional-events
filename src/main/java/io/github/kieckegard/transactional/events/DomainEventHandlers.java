/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public interface DomainEventHandlers {
    
    DomainEventHandler get(DomainEvent domainEvent);
}
