/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events.demo;

import io.github.kieckegard.transactional.events.DomainEvent;
import io.github.kieckegard.transactional.events.DomainEventRepository;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class InMemoryDomainEventRepository implements DomainEventRepository {
    
    private Map<String, DomainEvent> domainEvents = new ConcurrentHashMap<>();
    private static final Logger LOGGER = Logger.getLogger(InMemoryDomainEventRepository.class.getName());
    
    
    @Override
    public Stream<DomainEvent> listUnhandled(int size) {
        LOGGER.log(Level.INFO, "List {0} unhandled events", size);
        return this.domainEvents.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(DomainEvent::isUnhandled);
    }

    @Override
    public void markAsHandled(String eventId) {
        LOGGER.log(Level.INFO, "Marking {0} as handled", eventId);
        DomainEvent existent = this.domainEvents.get(eventId);
        if (existent != null) {
            existent.setHandledAt(LocalDateTime.now());
        }
    }

    @Override
    public void persist(DomainEvent domainEvent) {
        LOGGER.log(Level.INFO, "Persisting {0}", domainEvent);
        
        if (domainEvent.getId() == null) {
            domainEvent.setId(UUID.randomUUID().toString());
        } 
        
        final DomainEvent existent = domainEvents.get(domainEvent.getId());
        if (existent == null) {
            this.domainEvents.put(domainEvent.getId(), domainEvent);
        }
    }
    
}
