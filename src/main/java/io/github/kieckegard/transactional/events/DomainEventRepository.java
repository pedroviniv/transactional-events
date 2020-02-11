/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events;

import java.util.stream.Stream;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public interface DomainEventRepository {
    
    Stream<DomainEvent> listUnhandled(int size);
    void markAsHandled(String eventId);
    void persist(DomainEvent domainEvent);
}
