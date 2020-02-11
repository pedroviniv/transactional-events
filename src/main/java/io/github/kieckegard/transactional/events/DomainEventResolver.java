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
public class DomainEventResolver {
    
    private DomainEventHandlers domainEventHandlers;
    private DomainEventRepository domainEventRepository;

    public DomainEventResolver(DomainEventHandlers domainEventHandlers, DomainEventRepository domainEventRepository) {
        this.domainEventHandlers = domainEventHandlers;
        this.domainEventRepository = domainEventRepository;
    }
    
    //@Transactional
    public void resolve(DomainEvent domainEvent) {
        
        final DomainEventHandler domainEventHandler = 
                this.domainEventHandlers.get(domainEvent);
        
        domainEventHandler.handle(domainEvent);
        this.domainEventRepository.markAsHandled(domainEvent.getId());
    }
}
